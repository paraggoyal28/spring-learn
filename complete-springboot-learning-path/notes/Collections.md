# Java Collections

Iterable 
 |
Collection
 |- List
 |- Set
 |- Queue

Map -> separate from collection

## Iterable
Root interface
for (String name: names) {
    System.out.println(name);
}

## Collection
Parent interface of List, Set and Queue

### List
* Ordered
* Allows duplicates
* Index based access

Examples:
ArrayList
LinkedList
Vector

### Set
* Does not allow duplicate elements
* Used when uniqueness is required

Examples:
HashSet
LinkedHashSet
TreeSet

### Queue
* Used for processing elements, commonly in FIFO order

Examples:
PriorityQueue
LinkedList
ArrayDeque

### Map
* Not a child of collection
* Stored data as key-value pairs

Map<Integer, String> mp = new HashMap<>();

mp.put(1, "Parag");
mp.put(2, "Rahul");

1->Parag
2->Rahul

Important Implementation:
* HashMap
* LinkedHashMap
* TreeMap
* HashTable

Interview Question
Q. Why is Map not part of Collection ? 
Because Collection represents a group of individual elements, while Map represents 
key-value mappings

### List
Ordered collection that allows duplicate elements and provide index based access

ArrayList is a resizable array implementation of List
ArrayList<Integer> list = new ArrayList<>();

list.add(10);
list.add(20);
list.add(30);

System.out.println(list.get(1));
// 20

Important:
* Fast random access: O(1)
* Allows duplicates
* Maintains insertion order
* Not synchronized

LinkedList
Doubly linked list used internally

LinkedList<Integer> list = new LinkedList<>();
list.add(10);
list.add(20);
list.addFirst(40);
list.addLast(60);

#### ArrayList Internal Working
Internally uses a dynamic array
When the internal array becomes full, ArrayList creates a larger array, copies the existing 
elements, and continues adding.

Important complexities:
get() -> O(1)
add at end -> O(1) amortized
Insert/Delete at middle -> O(n)
Search -> O(n)

Interview: ArrayList is fast for reading/accessing elements but slower for frequent 
insertion/deletion in middle

#### LinkedList Internal Working
Consists of nodes
Each node contains:
[Previous | Data | Next]

Important complexities:
Access by index: O(n)
Add/remove at beginning -> O(1)
Add/remove at end -> O(1)
Search -> O(n)

#### ArrayList vs LinkedList

| ArrayList | LinkedList | 
| ---- | ---- |
| Dynamic Array | Doubly Linked List |
| get() -> O(1) | get() -> O(n) |
| Middle insertion/deletion -> O(n) | Node insertion/deletion -> (1) when node is located |
| Search - O(n) | Search - O(n) | 
| Less memory overhead | More memory due to node references | 
| General list usage | Frequent structural changes at known positions are needed | 


### Set
Does not allow duplicate elements

Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.add(10);

System.out.println(set);
// [10, 20]

#### HashSet
Uses hash table internally
- No duplicates
- No guaranteed order
- Allows one null
- Average add(), remove(), contains() -> O(1)

HashSet<Integer> set = new HashSet<>();

set.add(30);
set.add(10);
set.add(20);

#### LinkedHashSet
Maintains insertion order

LinkedHashSet<Integer> set = new LinkedHashSet<>();

set.add(30);
set.add(10);
set.add(20);

System.out.println(set);

// 30, 10, 20

- No duplicates
- Maintains insertion order
- Average base operations -> O(1)
- Allows one null

#### TreeSet

Stores elements in sorted order

TreeSet<Integer> set = new TreeSet<>();


set.add(30);
set.add(10);
set.add(20);

System.out.println(set);

// 10, 20, 30

- No duplicates
- Sorted/Natural order
- Basic operations - O(log n)
- Does not allow null 

#### HashSet Vs LinkedHashSet Vs TreeSet

| HashSet | LinkedHashSet | TreeSet |
| No duplicates | No duplicates | No duplicates | 
| No guaranteed order | Insertion order | Sorted order |
| One null key | One null key | No null keys |
| O(1) | O(1) | O (logn) | 


### Queue

Queue - Used to store elements for processing in a particular order. 
Typically follows FIFO (first in, first out)

Queue<integer> q = new LinkedList<>();
q.add(10);
q.add(20);
System.out.println(q.poll());

// 10


ArrayDeque  resizable array implementation of Deque

ArrayDeque<Integer> dq = new ArrayDeque<>();

dq.addFirst(10);
dq.addLast(20);

Important:
* No null values
* Not thread safe
* Amortized O(1) insertion time
* O(1) remove first and last
* Can be used as Stack, Queue or Deque

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {
    public static void main(String[] args) {

        Deque<String> deque = new ArrayDeque<>();

        // 1. Add elements to both ends
        deque.addLast("Middle Element");
        deque.addFirst("Front element");
        deque.addLast("Back element");
        System.out.println("Deque: " + deque);

        // Output: ["Front element", "Middle element", "Back element"] 

        // 2. Examine elements at both ends
        System.out.println("Front is: " + deque.peekFirst()); // Output: Front element
        System.out.println("Back is: " + deque.peekLast()); // Output: Back element

        // 3. Remove elements from both ends
        String removedFront = deque.pollFirst();
        System.out.println("Removed from front: " + removedFront); // Output: Front element

        String removedBack = deque.pollLast();
        System.out.println("Removed from last: " + removedBack); // Output: Back element

        System.out.println("Deque: " + deque); // ["Middle element"]
    }
}

| Feature | ArrayDeque | PriorityQueue | 
| ---- | --- | --- |
| Ordering Principle | Positional Ordering (FIFO, LIFO, or manual double ended entry) | Priority Ordering (Elements are sorted by natural order or a custom Comparator) | 
| Core Use Cases | Maintaining the literal arrival sequence (e.g web request buffers, undo histories) | Processing tasks based on importance or urgency (eg. Dijkstra's Algorithm, scheduling) |
| Insertion Time | Amortized O(1) | O(logN) (Required heapifying up) | 
| Removal Time | O(1) Front or Back | O (log N) for the higest priority item |
| Null Elements | Not Allowed | Not Allowed | 

### Map

Stores data by key-value pairs
- keys are unique
- values can be duplicated

Map<Integer, String> map = new HashMap<>();
map.put(1, "Java");
map.put(2, "Spring");
System.out.println(map.get(1)); // Java

#### HashMap
* No guaranteed order
* Allows one null key
* Allow multiple null values
* Average get(), put(), remove() operations - O(1)
* Not thread safe

HashMap<Integer, String> map = new HashMap<>();
map.put(1, "Java");
map.put(2, "Spring");
map.put(null, "SQL");

#### LinkedHashMap
* Maintains insertion order by default
* Allows one null key
* Average basic operations - O(1)

LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
map.put(3, "C++");
map.put(1, "Java");
map.put(2, "Python");

S.O.P(map);
// [3=C++, 1=Java, 2=Python]

#### TreeMap
* Sorted by keys
* Basic operations -> O(log n)
* Does not allow null keys with natural ordering

TreeMap<Integer, String> map = new TreeMap<>();

map.put(30, "C");
map.put(20, "Java");
map.put(10, "Python");

S.O.P(map);
// [10=Python, 20=Java, 30=C]

#### HashTable
* Legacy, synchronized map
* Thread safe
* Does not allow null key or value
* Generally replaced by ConcurrentHashMap for modern concurrent applications

HashTable<Integer, String> table = new HashTable<>();
table.put(1, "Java");

#### ConcurrentHashMap

* Thread safe
* High concurrency
* Does not allow null keys or values
* Preferred over Hashtable for concurrent applications

ConcurrentHashMap<Integer, String> mp = new ConcurrentHashMap<>();

mp.put(1, "Java");
mp.put(2, "Spring");

#### WeakHashMap

Uses weak references for its keys
If a key is no longer strongly referenced elsewhere, it can be garbage collected, and its 
mappings may then disappear.

WeakHashMap<Object, String> map = new WeakHashMap<>();
Object key = new Object();
map.put(key, "Java");
key = null;

// key can now be garbage collected
Useful for certain cache-like structures where entries should not prevent keys from being
garbage collected

#### EnumMap

Designed for enum keys

* Keys must be enum constants.
* Fast and memory-efficient for enum keys.
* Maintains the natural order of enum constants.
* Does not allow null keys.

### Quick Comparison

| Map | Order | Thread Safe | Null Key |
| --- | ---- | ----- | ----- |
| HashMap | No guarantee | No | 1 | 
| LinkedHashMap | Insertion | No | 1 | 
| TreeMap | Sorted | No | No | 
| HashTable | No guarantee | Yes | No | 
| ConcurrentHashMap | No guarantee | Yes | No | 
| WeakHashMap | No guarantee | No | Yes | 
| EnumMap | Enum order | No | No | 

With natural ordering, TreeMap does not allow null key.

Tricky: Is ConcurrentHashMap simply a synchronized HashMap ?
No. It provides better concurrency than synchronizing an entire map

### When TreeMap allows null keys
Using custom comparator
To allow a null key, we must initialize the TreeMap using a constructor that takes a 
comparator. Java provides pre-built helper methods like Comparator.nullsFirst() or 
Comparator.nullsLast() to accomplish this easily.

import java.util.*;

public class Main {
    public static void main(String args[]) {
        // create a TreeMap that safely places null keys at the very beginning
        Map<String, String> map = new TreeMap<>(Comparator.nullsFirst(Comparator.naturalOrder()));

        map.put(null, "First value");
        map.put("Apple", "Second value");
        map.put(null, "Second value");
        map.put("Bat", "Third value");

        S.O.P(map); // [null=Second Value, Apple=Second Value, Bat=Third Value]
    }
}








