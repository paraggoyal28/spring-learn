# Java Collections

## Collections class
Collections class in Java is a utility class provided by Java Collections Framework that contain static methods for performing 
common operations on collections. It simplifies tasks like sorting, searching, and modifying collection elements efficiently.

Provides methods such as sort(), reverse(), shuffle(), and binarySearch().
Eg.
public class Geeks {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>();

        al.add("Apple");
        al.add("Banana");
        al.add("Apple");

        Collections.sort(al); // Using Collections class

        System.out.println(al);
    }
}

                                    Iterable (Interface) 
                                            |
                                    Collection (Interface) 
                                            |
        -------------------------------------------------------------------------------------
        |                                    |                                              |   
        List (Interface)               Queue (Interface)                                 Set (Interface)
         - ArrayList (class)                 |         |---- PriorityQueue (class)          |    -HashSet(class),LinkedHashSet(class)
         - LinkedList (class) --------- Deque (Interface)                                SortedSet (Interface)
         - Vector (class)                    |                                               |
            |                            ArrayDeque (class)                                TreeSet (class)
            - Stack (class)

                                        Map (Interface)
                                             |
                          HashMap(C) ---------| 
                                            SortedMap (I)
                                              | -- TreeMap (Class)


## Collection Interface

Collection Interface is the root of Java Collections Framework, defined in java.util package. 

public interface Collection<E> extends Iterable<E>


# List Interface
| Class/Factory | Best Use | Strengths | Tradeoffs | 
|-------------|------|-------|---------|
| ArrayList | Default general purpose list | Fast indexed reads, amortized fast appends, compact | Inserts/Removals in the middle shift elements | 
| LinkedList | Frequent adds/removes at both ends | Fast addFirst, removeFirst, addLast, removeLast | Slow index access, high memory overhead, often inferior to ArrayDeque for queues/stacks | 
| Vector | Legacy synchronized list | Thread-safe individual operations | Old API, synchronization overhead, usually prefer concurrent alternatives | 
| Stack | Legacy Stack | Simple push/pop | Extends Vector; use Deque/ArrayDeque instead | 
| CopyOnWriteArrayList | Many reads, very few writes, safe iteration | Thread-safe, iterators never throw ConcurrentModificationException | Every write copies the whole array; costly for write-heavy data | 
| Collections.synchronizedList(new ArrayList<>()) | Existing code needing a locked list | Easy thread-safe wrapper | Must synchronize externally while iterating; one lock limits concurrency | 
| List.of(...) | Immutable fixed contents | Compact, safe to share, no mutations | Rejects null, any mutation throws 
UnsupportedOperationException | 
| Arrays.asList(...) | Fixed size view of an array | Fast conversion, change reflect into the array | Cannot add/remove; can replace elements, accepts null | 
| Collections.unmodifiableList(list) | Read only view of another list | Prevents mutation through this reference | Original list still can change, and view reflects those changes | 
| List.copyOf(...) | Immutable snapshot | Independent immutable copy, safe to share | Rejects null; copies contents | 


## Immutable/Restricted lists 
List<String> fixed = Arrays.asList("A", "B");
fixed.set(0, "X");    // allowed
// fixed.add("C");    // fails: fixed size // java.lang.UnsupportedOperationException

List<String> immutable = List.of("A", "B"); // null elements cannot be passed here
// immutable.set(0, "X"); // fails
// immutable.add("C");    // fails // UnsupportedOperationException

List<String> source = new ArrayList<>(List.of("A", "B"));
List<String> view = Collections.unmodifiableList(source);

source.add("C");
System.out.println(view); // [A, B, C] — it is a view, not a copy

List<String> snapshot = List.copyOf(source);
source.add("D");
System.out.println(snapshot); // still [A, B, C]


