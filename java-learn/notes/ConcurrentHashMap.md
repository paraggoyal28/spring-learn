# ConcurrentHashMap: Modern Internals, Atomic Methods, and Industry Use

`ConcurrentHashMap` is Java's high-concurrency, in-memory key/value map. Use it when multiple threads in **one JVM** need to read and update shared mappings safely, without putting a single lock around the entire map.

```java
import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
```

This guide describes the modern OpenJDK implementation used from Java 8 onward. The public API contract is the part application code should rely on; details such as the exact lock object or resize mechanics can vary across JDK releases and vendors.

## Executive summary

| Question | Short answer |
|---|---|
| What problem does it solve? | Safe, high-throughput access to a shared map from many threads. |
| Are reads blocked by writes? | `get()` generally does not block and can overlap with updates. |
| Can two writers update different keys together? | Usually yes, especially when they land in different bins. |
| Can two writers update the same bin together? | Their conflicting updates are coordinated; one waits/retries. |
| Is a sequence of operations atomic? | Only if it is a documented atomic operation such as `putIfAbsent`, `compute`, or `merge`. |
| Are stored objects automatically thread-safe? | No. The map is safe; a mutable `ArrayList`, DTO, or other value inside it may not be. |
| Is it a distributed cache? | No. It exists only in the current JVM and has no expiry, eviction, persistence, or cross-process coordination. |

`ConcurrentHashMap` lives in the `java.util.concurrent` package:

```java
ConcurrentHashMap<String, Integer> counts = new ConcurrentHashMap<>();
```

## Why a shared HashMap is unsafe

`HashMap` is excellent for data owned by one thread. It is not safe for simultaneous modification without external coordination.

```java
// Unsafe when multiple threads execute it against a shared HashMap
if (!map.containsKey(key)) {
    map.put(key, createValue());
}
```

Two threads can both observe the key as absent, both create a value, and then race to insert it. More generally, concurrent structural changes to a plain `HashMap` can cause lost updates and unreliable observations.

Use this atomic operation instead:

```java
map.putIfAbsent(key, createValue());
```

Or use lazy initialization when the value should only be made when needed:

```java
map.computeIfAbsent(key, this::createValue);
```

## ConcurrentHashMap versus HashMap

| Capability | `HashMap` | `ConcurrentHashMap` |
|---|---|---|
| Safe concurrent reads and writes | No | Yes |
| `null` keys or values | Allows one null key and null values | Rejects both |
| Iteration while another thread mutates the map | Best-effort fail-fast | Weakly consistent and safe |
| Atomic conditional updates | Must be implemented with an external lock | Built in (`putIfAbsent`, `replace`, `compute*`, `merge`) |
| Update concurrency | None without external synchronization | Fine-grained; unrelated bins commonly progress together |
| Overhead | Lowest for thread-confined data | Extra coordination and memory overhead |

The no-`null` rule is useful: `get(key) == null` reliably means “there is no current mapping.”

## Public guarantees to rely on

The official JDK documentation describes a hash table with full concurrent retrievals and high expected update concurrency. A completed update for a key happens-before a later non-null retrieval of that updated value for the same key. Retrievals generally do not lock or block. Iterators can run while the map changes, but they are not a snapshot and may see some concurrent changes but not others. Aggregate status methods such as `size()`, `isEmpty()`, and `containsValue()` can reflect a transient state during active updates, so do not use them as the sole basis for critical control flow.

See the [official ConcurrentHashMap Javadoc](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/concurrent/ConcurrentHashMap.html) for the API contract.

Two important boundaries:

- Atomicity is normally per mapping/key, not across multiple keys or multiple statements.
- Thread safety covers the map structure, not the state of mutable values stored in it.

## How the modern implementation works

### 1. Table, hashes, and bins

At a high level, the map has an array-like table. A spread version of a key's hash selects a bin.

```text
key → hash / hash spread → table index → bin

table
  [0] → Node → Node
  [1] → empty
  [2] → TreeBin
  [3] → Node
```

A normal bin holds a short linked chain of `Node` entries. If a bin becomes heavily collided, OpenJDK can use a balanced tree (`TreeBin`/`TreeNode`) rather than leaving it as a long list. In current OpenJDK source, treeification is considered around eight entries and only once the table is sufficiently large; otherwise growing the table is generally preferable.

Good, stable `hashCode()` and `equals()` implementations still matter. A hot bin caused by many collisions means less concurrency and slower operations.

### 2. Reads: generally lock-free

For a typical `get(key)`, the map computes the bin, follows its nodes or tree, and returns the matching value. The table and node fields use memory-visibility mechanisms such as volatile reads, so readers can safely observe completed updates without acquiring a whole-map or ordinary bin lock.

```text
Thread A: put("customer-42", value)
Thread B: get("customer-42")  → safely observes the completed mapping
```

This does not make a whole-map view atomic: while writers are active, an iterator or `size()` can observe a valid but changing state.

### 3. Writes: CAS first, then fine-grained coordination

For an update such as `put`, the modern implementation broadly follows this path:

```text
1. Calculate the key's hash and table index.
2. If the bin is empty, attempt to install the first node with CAS.
3. If another thread installed something first, retry against the current bin.
4. If the bin is non-empty, coordinate on that bin, find/update/add the mapping.
5. If collisions are excessive, treeify or resize as appropriate.
```

CAS means compare-and-set: “place this first node only if the table slot still contains the expected value.” It avoids taking a traditional lock for the common empty-bin insertion case.

For a non-empty linked bin, OpenJDK commonly uses the bin's first node as the monitor for a `synchronized` update. A `TreeBin` provides stable coordination when the bin has become a tree. This is not a permanent `ReentrantLock` allocated for every table slot, and it is not a global map lock.

```text
Same non-empty bin

Thread A → coordinates on bin 5 → changes mapping → releases
Thread B → waits/retries for bin 5 → changes mapping

Different bins

Thread A → updates bin 5
Thread B → updates bin 19     } can usually proceed concurrently
```

### 4. Collisions and trees

Two different keys can have hashes that lead to the same table index. That is a collision. A linked list is efficient when collisions are rare. Under sustained collisions, a balanced tree keeps worst-case lookup closer to `O(log n)` rather than walking a long list.

This protects the map from poor hash distribution but does not make poor keys free: use correct `equals()`/`hashCode()` implementations and avoid intentionally collision-heavy keys.

### 5. Cooperative resizing

When the map needs more capacity, it creates a larger table and moves bins. Modern OpenJDK allows other threads that encounter an in-progress resize to help transfer ranges of bins. A `ForwardingNode` in a moved bin directs later traversals to the new table.

```text
old table bin → moved → ForwardingNode → new table
```

The important outcome is that resizing does not require one thread to hold a global map lock while every mapping moves. It can still be expensive, so provide a realistic `initialCapacity` if a large number of entries is known in advance.

### 6. Counting under contention

The implementation uses striped counting techniques internally to reduce contention when many threads update the map. Consequently, `size()` during continuous mutation is useful for monitoring or estimation, not for a transactional decision such as “only insert if the map contains fewer than N entries.”

The [OpenJDK source commentary](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/util/concurrent/ConcurrentHashMap.java) explains these nodes, tree bins, forwarding nodes, and cooperative resize behavior.

### References
* https://javaconceptoftheday.com/how-concurrenthashmap-works-internally-after-java-8/
* https://javatrainingschool.com/concurrenthashmap-in-java/