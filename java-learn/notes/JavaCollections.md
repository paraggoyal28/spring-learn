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


## Queue/Stack/Deque

Queue and Deque are interfaces. Deque extends Queue, so every deque can also be used as a queue. Stack is not an interface - it
is a legacy concrete class extending Vector. Java recommends using a Deque, normally ArrayDeque, instead of Stack for LIFO
operations.

| Class | Package | Main Purpose | 
| -------- | ------- | ---------- |
| AbstractQueue | java.util | Base class for writing custom queues | 
| ArrayDeque | java.util | General-purpose queue, stack, and deque | 
| LinkedList | java.util | LinkedList that also implements Deque | 
| PriorityQueue | java.util | Processes elements according to priority | 
| ArrayBlockingQueue | java.util.concurrent | Fixed-capacity blocking FIFO queue |
| LinkedBlockingQueue | java.util.concurrent | Optionally bounded blocking FIFO queue | 
| LinkedBlockingDeque | java.util.concurrent | Blocking queue/deque at both ends | 
| PriorityBlockingQueue | java.util.concurrent | Thread-safe blocking priority queue | 
| ConcurrentLinkedQueue | java.util.concurrent | Unbounded, thread-safe, non-blocking FIFO queue | 
| ConcurrentLinkedDeque | java.util.concurrent | Unbounded thread-safe deque |
| DelayQueue | java.util.concurrent | Elements become removable after a delay | 
| LinkedTransferQueue | java.util.concurrent | Queue supporting direct producer-to-consumer transfer | 
| SynchronousQueue | java.util.concurrent | Direct handoff with no internal storage | 

### ArrayDeque
Non-thread safe queue, stack, or deque. It grows as required, prohibits null. Preferred over Stack for stacks and LinkedList for queues.
As a FIFO queue
import java.util.ArrayDeque;
import java.util.Queue;

Queue<String> customers = new ArrayDeque<>();

customers.offer("Alice");
customers.offer("Bob");
customers.offer("Charlie");

System.out.println(customers.poll()); // Alice
System.out.println(customers.poll()); // Bob

As a LIFO stack
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.peek()); // 30
System.out.println(stack.pop());  // 30
System.out.println(stack.pop());  // 20

As a double ended queue
Deque<String> deque = new ArrayDeque<>();

deque.offerFirst("B");
deque.offerFirst("A");
deque.offerLast("C");

System.out.println(deque);           // [A, B, C]
System.out.println(deque.pollFirst()); // A
System.out.println(deque.pollLast());  // C

### LinkedList
LinkedList implements Deque and List. It supports queue, stack and deque operations, but not synchronized.
import java.util.LinkedList;
import java.util.Queue;

Queue<String> queue = new LinkedList<>();

queue.offer("First");
queue.offer("Second");

System.out.println(queue.poll()); // First

Can be used as a deque
LinkedList<Integer> deque = new LinkedList<>();

deque.addFirst(20);
deque.addFirst(10);
deque.addLast(30);

System.out.println(deque); // [10, 20, 30]

For a basic queue or stack, prefer ArrayDeque unless you specifically need LinkedList functionality.

### PriorityQueue
By default, PriorityQueue uses the natural ordering of elements (ie. ascending order of integers).

import java.util.PriorityQueue;

public class Main {
    public static void main(String args[]) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(10);
        pq.offer(30);
        pq.offer(20);

        // Elements are polled in ascending order
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}

To reverse the order, you can pass Collections.reverseOrder() or a custom comparator to the 
constructor

import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        // Using reverseOrder to make it max heap
        PriorityQueue<Integer> maxHeapPQ = new PriorityQueue<>(Collections.reverseOrder());

        maxHeapPQ.offer(10);
        maxHeapPQ.offer(20);
        maxHeapPQ.offer(30);

        while (!maxHeapPQ.isEmpty()) {
            System.out.println(maxHeapPQ.poll());  // Prints 30, 20, 10
        }
    }
}

Custom Objects 
import java.util.PriorityQueue;
import java.util.Comparator;

class Task {
    String name;
    int priority;

    Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(Comparator.comparingInt(t -> t.priority));

        taskQueue.offer(new Task("Low Priority", 10));
        taskQueue.offer(new Task("High Priority", 1));

        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll().name); // 
        }
    }
}

Method                     Complexity
offer(e)                    O(logn)
poll()                      O(logn)
peek()                      O(1)
contains(e)                 O(n)

Remember that PriorityQueue is not synchronized. If we need a thread-safe version for multi-threaded
applications use PriorityBlockingQueue instead.

### ArrayBlockingQueue

Bounded, blocking queue backed by array. Ideal for Producer-Consumer patterns because it automatically
handles thread synchronization and flow control. When the queue is full, the producer will block (wait)
until space becomes available. When the queue is empty, the consumer will block until an item is added.

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        // Bounded capacity of 5
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        // Producer Thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i <= 10; ++i) {
                    System.out.println("Producing " + i); 
                    queue.put(i); // Blocks if queue is full
                    Thread.sleep(1000);  // Simulate work
                }  
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Integer item = queue.take(); // Blocks if queue is empty
                    System.out.println("Consumed: " + item);
                    Thread.sleep(1000);
                } 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}


Handling Timeouts (Non-indefinite Blocking)
In many real-world system designs, you do not want your threads to wait indefinitely. You can 
use offer and poll with a timeout to prevent system deadlocks or hang-ups.

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TimeoutExample {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

        // Offer with timeout: try to add, but wait only 2 seconds if full
        boolean inserted = queue.offer("Task 1", 2, TimeUnit.SECONDS);
        System.out.println("Inserted: " + inserted);

        // Poll with timeout: try to take, but wait only 2 seconds if empty
        String item = queue.poll(2, TimeUnit.SECONDS);
        System.out.println("Retrieved: " + item);
    }
}

Why use ArrayBlockingQueue ? 

1. Memory Efficiency: Because it is array-backed, it has a fixed memory footprint. It avoids node
allocation overhead of LinkedBlockingQueue.
2. Fairness policy: You can enable a "fairness" parameter in the constructor new ArrayBlockingQueue<>(capacity, true). This ensures that the longest waiting threads are granted access first, preventing
thread starvation.
3. Backpressure: The bounded nature naturally provides backpressure, preventing a fast producer from
overwhelming a slow consumer and causing OutOfMemory error.

### LinkedBlockingQueue

LinkedBlockingQueue is an optionally bounded, blocking queue based on linked nodes. Unlike ArrayBlockingQueue, which is array backed and required a fixed capacity, LinkedBlockingQueue can be unbounded (by using default constructor), meaning it will grow until it hits the memory limit 
(or Integer.MAX_VALUE).

It uses a "two-lock queue" algorithm (separate locks for head and tail), which allows for higher 
throughput in concurrent applications because producers and consumers can operate on different ends
of queue simultaneously.

1. Unbounded Queue (Default Constructor)

If you don't specify a capacity, the queue is essentially unbounded. Use this with caution, as a fast
producer can easily lead to OutOfMemoryError. If the consumer cannot keep up.

import java.util.concurrent.LinkedBlockingQueue;

public class UnboundedExample {
    public static void main(String[] args) {
        // Capacity is Integer.MAX_VALUE
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        queue.add("Task A");
        queue.add("Task B");

        System.out.println("Queue size: " + queue.size());
    }
}


2. Bounded Usage (High-scale Producer Consumer)

Providing a specific capacity makes the queue bounded. This is the recommended approach for stable 
system design to avoid backpressure.

import java.uti.concurrent.LinkedBlockingQueue;

public class BoundedLinkedQueueExample {
    public static void main(String[] args) {
        // Bounded capacity of 1000
        LinkedBlockingQueue<String> logQueue = new LinkedBlockingQueue<>(1000);

        // Producer: Submitting log entries
        new Thread(() -> {
            try {
                logQueue.put("Log entry: System heartbeat");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer: Processing Logic
        new Thread(() -> {
            try {
                String log = logQueue.take();
                System.out.println("Processing: " + log);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}

Which one should you use ? 
1. Use ArrayBlockingQueue: When you want to minimize GC impact (by reusing the underlying array)
and have a predictable, fixed sized workload. It is often preferred in low-latency systems where
object allocation overhead is a concern.
2. Use LinkedBlockingQueue: When you expect high contention. Because it uses separate locks for 
head (removal) and tail (insertion), it allows multiple threads to interact with the queue at the same
time more effectively than the single lock design of ArrayBlockingQueue. 

### LinkedBlockingDeque
LinkedBlockingDeque is the thread-safe, double-ended counterpart to LinkedBlockingQueue. Allows 
insertion and removal of elements from both ends (head and tail) of the queue. Like LinkedBlockingQueue,
it can be optionally bounded and uses linked nodes.

Because it supports operations on both ends, it uses a single lock for both head and tail operations 
(unlike the two lock design of LinkedBlockingQueue), which can impact performance under extremely 
high contention.

"Work-stealing" pattern
This is a common architectural pattern in systems like the Java ThreadJoinPool, where threads process 
their own work but can help others if they run out of tasks.

import java.util.concurrent.LinkedBlockingDeque;

public class WorkStealingExample {
    public static void main(String[] args) {
        LinkedBlockingDeque<String> taskDeque = new LinkedBlockingDeque<>(50);

        // Thread1: The "Owner" adds tasks and processes from the head
        new Thread(() -> {
            taskDeque.addLast("Main Task A");
            taskDeque.addLast("Main Task B");

            while (!taskDeque.isEmpty()) {
                System.out.println("Owner Processing: " + taskDeque.pollFirst());
            }
        }).start();

        // Thread2: The "Helper" steals from the end
        new Thread(() -> {
            try {
                // Helps by taking from the end to avoid contention with owner
                String stolenTask = taskDeque.takeLast();
                System.out.println("Helper stole: " + stolenTask);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}

| Operation Type | Head (First) | Tail (Last) |
| -------- | --------- | --------- |
| Insert (Blocking) | putFirst(e) | putLast(e) | 
| Remove (Blocking) | takeFirst() | takeLast() |
| Insert (Non-Blocking) | offerFirst(e) | offerLast(e) | 
| Remove (Non-Blocking) | pollFirst() | pollLast() | 

When to choose LinkedBlockingDeque ? 
1. Work stealing algorithms: Most common use case of double ended blocking collections.
2. LIFO/FIFO Flexibility: If we need a single collection for Stack and Queue in a thread-safe manner.
3. Complex Scheduling: When you need to prioritize specific "emergency" tasks by pushing them to the 
front while standard tasks continue to be added in the back.

### PriorityBlockingQueue

PriorityBlockingQueue is an unbounded, thread-safe, blocking queue that orders elements based on
their priority. Because it is unbounded, put() and offer() operations never block (they will
only expand if memory allows), however take() will block if queue is empty.

Highly useful for scenarios when multiple threads feeding tasks with varying levels of of urgency 
into the system, and we need to ensure the most important tasks are processed first.

Multi-Threaded Producer Consumer
Unlike ArrayBlockingQueue, PriorityBlockingQueue is unbounded. This means producers don't block
even if the queue grows quite large.

import java.util.concurrent.PriorityBlockingQueue;

public class AsyncPriorityProcessing {
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        // Producer: Adding data sporadically
        Thread producer = new Thread(() -> {
            queue.put(10);
            queue.put(30);
            queue.put(20);
        });

        // Consumer: Processing in priority order
        Thread consumer = new Thread(() -> {
            try {
                while(true) {
                    Integer val = queue.take(); // Blocks until data available
                    System.out.println("Processing: " + val);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}

Comparison: PriorityBlockingQueue VS. ArrayBlockingQueue

| Feature | PriorityBlockingQueue | ArrayBlockingQueue |
| -------- | --------- | ------------- |
| Ordering | Priority based (Heap) | FIFO (Insert order) | 
| Capacity | Unbounded | Fixed (Bounded) | 
| Blocking Behaviour | put() never blocks | put() blocks when full | 
| Best Used for | Urgent/Priority task scheduling | Throttling/Backpressure management |

Pro-Tip for your Architectural WOrk:
Because PriorityBlockingQueue is unbounded, it is susceptible to OutOfMemoryError. If your 
producers are significantly faster than your consumers. If you need a bounded priority queue, 
you would typically need to wrap a PriorityQueue with custom locks and a Condition variable, or 
use a third-party library, as Java does not provide a built-in bounded priority blocking queue.

### ConcurrentLinkedQueue

ConcurrentLinkedQueue is an unbounded, thread-safe, non-blocking queue based on linked nodes.
Unlike the BlockingQueue family, it does not use locks to ensure thread safety; Instead it relies
on efficient Wait-Free/Lock-Free algorithms (especially CAS-Compare-And-Swap) to achieve high performance.
Because it is non-blocking, it is the ideal choice for high throughput, low-latency applications
where threads cannot afford to wait (block) for the queue to have space of items.

High Throughput Producer-Consumer

import java.util.concurrent.ConcurrentLinkedQueue;

public class HighThroughputExample {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> eventQueue = new ConcurrentLinkedQueue<>();

        // Producer: fast, non-blocking submission
        Runnable producer = () -> {
            for (int i = 0;i < 1000; ++i) {
                eventQueue.offer(i);
            }
        };

        // Consumer: Polling for tasks
        Runnable consumer = () -> {
            while (true) {
                Integer task = eventQueue.poll();
                if (task != null) {
                    System.out.print(task + " ");
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

Key Technical Considerations:
1. Wait-Free/Lock-Free: Threads never sleep while waiting for access to the queue.
2. Weakly Consistent Iterators: Iterators for this queue are weakly consistent. They reflect the 
state of the queue at the time the iterator was created and may not reflect subsequent modifications.
They will never throw ConcurrentModificationException.
3. Unbounded: Like PriorityBlockingQueue, this queue is unbounded. A "runaway" producer can exhaust 
memory if consumer is slower than the producer.

We might consider ConcurrentLinkedQueue for in-memory event buffers where dropping a task is better 
than blocking the main execution path.

### ConcurrentLinkedDeque

ConcurrentLinkedDeque is the thread-safe, lock-free equivalent of a Deque. It uses the same non-blocking 
CAS-based (Compare And Swap) algorithms as ConcurrentLinkedQueue, but allows for bidirectional access.

Because it is non-blocking, it is the performance oriented choice for high-concurrency scenarios that 
require double-ended operations, such as work-stealing or complex scheduling.

High-Performance Work Stealing
In a multi-threaded systems, A main thread adds tasks to one end of deque, and worker threads 
"steal" tasks from the opposite end, minimizing contention on the pointers.

import java.util.concurrent.ConcurrentLinkedDeque;

public class WorkStealingExample {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<Integer> taskDeque = new ConcurrentLinkedDeque<>();

        // Producer: Adding tasks (e.g task submission)
        taskQueue.addLast("Job A");
        taskQueue.addLast("Job B");

        // Consumer: Stealing from the front/back
        // This is non-blocking, so it's extremely fast
        String task = taskQueue.pollFirst();

        System.out.println("Processing: " + task);
    }
}

Common Use-Cases
1. Asynchronous Processing with Priority Overrides: If you are processing standard events (added via
addLast) but need to inject an "urgent" system task, you can use addFirst to ensure the next consumer
picks it immediately, all without the overhead of heavy locks.
2. Low-latency buffers: Since it is lock-free, it avoids the thread suspension and context switching 
associated with LinkedBlockingDeque. If your system must maintain high throughput while handling many 
threads, this is often the most performant structure for Deque.
3. Weakly consistent Iterator: Like the ConcurrentLinkedQueue, the iterators are weakly consistent. 
You can safely iterate over the collection while other threads are adding or removing elements, and you
will never receive a ConcurrentModificationException.

| Feature | ConcurrentLinkedDeque | LinkedBlockingDeque | 
| ------- | ---------- | -------- |
| Concurrency Logic | Lock-free (CAS) | Blocking (Locks/Conditions) | 
| Throughput | Very High (Non-Blocking) | Moderate (Lock Contention) | 
| Behavior when empty | Returns null (fast) | Waits (blocks the thread) | 
| Memory | Unbounded | Optionally bounded | 


When choosing between ConcurrentLinkedDeque and LinkedBlockingDeque, consider these two factors:
1. Backpressure: If you need to stop a producer when the system is overloaded, use LinkedBlockingDeque
(the blocking mechanism provides natural backpressure).
2. Latency: If your requirement is to keep the producer moving at all costs and you have an 
alternative way to handle overflows (e.g shedding loads, logging or monitoring), ConcurrentLinkedDeque
is superior because it will never block the producer thread.

### DelayQueue

DelayQueue is a specialized, unbounded blocking queue where elements can only be taken when their 
delay has expired. It is internally backed by PriorityQueue, meaning the element with the shortest 
(most expired) delay is always at the head of the queue.
To use DelayQueue, the elements must implement the java.util.concurrent.Delayed interface.
1. Requirements for Elements
We must implement getDelay(TimeUnit unit) to tell the queue how much time is left, and compareTo(Delayed other) to allow the queue to order elements by their expiration times.

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedTask implements Delayed { 
    final String taskName;
    final long startTime;

    public DelayedTask(String taskName, long delayInMilliseconds) {
        this.taskName = taskName;
        this.startTime = System.currentTimeMillis() + delayInMilliseconds;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.Milliseconds);
    }

    @Override
    public int compareTo(Delayed other) {
        if (this.startTime < ((DelayedTask) other).startTime) return -1;
        if (this.startTime > ((DelayedTask) other).startTime) return 1;
        return 0;
    }
}

Basic Usage Pattern
In the below example, the consumer thread blocks until a thread's delay period is actually over.

import java.util.concurrent.DelayQueue;

public class DelayQueueExample {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();

        // Add tasks with different delays
        queue.put(new DelayedTask("Task 1 (short)", 1000));
        queue.put(new DelayedTask("Task 2 (long)", 5000));

        // Consumer blocks until the delay expires
        while (true) {
            DelayedTask task = queue.take();
            System.out.println("Processing: " + task.taskName + " at " + System.currentTimeMillis());
        }
    }
}

Common Use Cases:
1. Scheduled Task Execution: Running background tasks after a specific timeout (eg. closing a 
connection after 30 seconds of inactivity).
2. Cache expiration: Automatically removing expired items from the cache.
3. Retry Mechanisms: Re-queuing a failed network request with an increasing "backoff" delay.
4. Token Bucket Rate Limiting: Tracking when tokens should be replenished in an API rate limiter.

Engineering Considerations:
1. Thread Safety: DelayQueue is fully thread safe for producers and consumers.
2. Unbounded Nature: Like other unbounded queues, it can grow until you run out of memory. If you 
are scheduling millions of tasks, monitor the size of the queue to prevent OutOfMemoryError.


### SynchronousQueue
SynchronousQueue is a unique, zero-capacity blocking queue. It does not store elements, rather, it acts
as a hand-off mechanism where a producer thread passes an element directly to a consumer thread.
If no thread is waiting to take an element, the producer will block until a consumer arrives. 
Conversely, if no thread is waiting to put an element, the consumer will block until a producer arrives.

Example:
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        // Producer Thread
        new Thread(() -> {
            try {
                System.out.println("Producer trying to put...");
                queue.put("Hand off data"); // Blocks until consumer takes it
                System.out.println("Producer finished...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer Thread
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate some delay
                System.out.println("Consumer ready to take ..... ");
                String data = queue.take(); // Blocks until producer puts it
                System.out.println("Consumer received: " +  data);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}

Architectural Significance: The Executors.newCachedThreadPool()
One of the most famous uses of SynchronousQueue in Java is inside Executors.newCachedThreadPool().
When you create a cached thread pool, it uses a SynchronousQueue to hand off tasks directly to threads.
If a thread is available, it takes the task. If not, the pool creates a new thread. This allows for 
extremely low latency, because tasks are not buffered in a queue - they are handed directly to workers.

Key Characteristics:
1. Zero capacity.
2. Direct Handoff: Fastest way to communicate between threads because there is no intermediate memory 
overhead.
3. Fairness: If fairness is enabled, it uses a FIFO queue for waiting threads, otherwise LIFO stack.
fair parameter is in the constructor new SynchronousQueue(true)

Comparison: SynchronousQueue vs ArrayBlockingQueue

| Scenario | Use SynchronousQueue | Use ArrayBlockingQueue |
| ------- | ------- | ----- |
| Throughput | High (direct hand-off) | High (Buffered) | 
| Latency | Extremely Low (No queueing) | Slightly higher (Buffered Latency) | 
| Load Handling | No buffer, requires matching threads | Buffer spikes in traffic | 
| Core Use Case | Task-stealing or thread-pooling | Producers/Consumers with uneven speeds | 

SynchronousQueue is a powerful tool to prevent "buffer bloat" if we are building an ingestion layer 
where we want the producer to throttle immediately if the system is at capacity, SynchronousQueue is 
ideal because it lacks any buffer to hide the fact that the system is saturated.


### LinkedTransferQueue

LinkedTranferQueue is a highly-efficient, hybrid collection that combines the features of a 
SynchronousQueue, LinkedBlockingQueue, and ConcurrentLinkedQueue.

It is a dual data structure that supports a "transfer" mechanism. Unlike a standard queue, a 
producer can choose to block until a consumer actually accepts the item, or it can simply offer 
the item if no consumer is currently waiting.

Key Methods 

| Method | Behaviour | 
| ----- | ----- |
| transfer(e) | Blocks until a consumer consumes the item | 
| tryTransfer(e) | If a consumer is waititng, hands off immediately; otherwise, return false (no blocking) | 
| tryTransfer(e, time, unit) | Waits for a consumer for a specific time, then returns false if not consumed |
| put(e) / add(e) | Behaves like a normal LinkedBlockingQueue (inserts into the queue) | 

To provide a concrete, production-style example, let's look at a Request Processing Pipeline. In this
scenario, we use transfer() to ensure a critical request is handled immediately by a worker, while 
using put() for standard, background tasks that can wait in the queue if the workers are busy.

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class RequestPipeline {
    private static final TransferQueue<String> taskQueue = new LinkedTransferQueue<>();

    public static void main(String args[]) {

        // 1. Consumer - The worker thread
        Thread worker = new Thread(() -> {
            try {
                while (true) {
                    // Takes whatever is available (transferred or queued)
                    String task = taskQueue.take();
                    System.out.println("[worker] Processing: " + task);
                    Thread.sleep(1000); // Simulate work
                } 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } 
        });

        worker.setDaemon(true);
        worker.start();

        // 2. Producer - Sending different types of tasks
        try {
            // Scenario A: High Priority - Direct Hand off
            // The producer BLOCKS here until the worker calls take()
            System.out.println("[Producer] Transferring CRITICAL task......");
            taskQueue.transfer("CRITICAL_Request_001");
            System.out.println("[Producer] CRITICAL_Request_001 was picked up instantly");

            // Scenario B: Standard Task - Buffer
            // The producer DOES NOT block; task goes to queue
            System.out.println("[Producer] Putting STANDARD task.....");
            taskQueue.put("STANDARD_LOG_UPDATE");
            System.out.println("[Producer] Standard Task is in queue");
            
            // Scenario C: Attempt Direct Handoff
            // If worker is busy it returns false instead of blocking
            boolean success = taskQueue.tryTransfer("FAST_PING");
            if (!success) {
                System.out.println("[Producer] No worker free for immediate transfer, queueing instead...");
                taskQueue.put("FAST_PING");
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

Advantages:
1. Reduced Lock Contention: Because LinkedTransferQueue uses lock-free (CAS) operations, this pipeline
will outperform a LinkedBlockingQueue under high thread contention. (many producers and consumers 
hitting the queue simultaneously).
2. Adaptive Backpressure:
a. Strict Mode (transfer): You can force your system to process a task immediately by blocking the 
producer. This prevents the queue from growing during a critical event.
b. Loose Mode (put): You can allow background tasks to buffer up without stalling the main execution 
thread.
3. Efficiency: In the tryTransfer example, you essentially implement a "Fast-Path" strategy. If a worker is idle, you skip the overhead of adding to the queue and re-fetching; you effectively "hand" the work to the worker thread directly, which is cache-friendly and reduces latency.


## Set

### HashSet
Implements Set interface.
Store unique elements, and it doesn't maintain any specific order of elements,
Does not allow duplicate elements.
Uses HashMap internally which is an implementation of hash table data structure.
Also implements Serializable and Cloneable interface.
Not thread-safe, to make it thread-safe, synchronization is needed externally.

Capacity refers to the number of buckets in the hash table.
The default capacity of HashSet is 16 and load factor is 0.75.
When the number of elements increases the threshold, the capacity doubles.
Threshold = 16 * 0.75 = 12.
HashSet handles collisions using chaining (LinkedList) and converts it to a balanced tree 
(Red-Black Tree) when the number of elements in a bucket exceeds the threshold.

Performance: O(1)

Set<String> set = new HashSet<>();
set.add("Apple");
set.add("Banana");
set.add("Apple"); // Ignored, duplicates not allowed

Practical Tip: If you expect a large number of elements, provide an initial capacity to the constructor (e.g., new HashSet<>(64)) to avoid frequent resizing

### LinkedHashSet
Implements Set interface and maintains insertion order while storing unique elements.
Combines features of HashSet and a LinkedList.
Maintains insertion order of elements.
Stores unique elements only (no duplicates).
Provides fast performance for basic operations.
LinkedHashSet<String> set = new LinkedHashSet<>();
set.add("Apple");
set.add("Banana");
set.add("Cherry");
set.add("Apple");
System.out.println("" + set);

Output:
[Apple, Banana, Cherry]

If an element is removed and then added again, it is inserted at the end of the LinkedHashSet, because insertion order is maintained based on latest insertion.

### TreeSet
Stores unique elements in sorted order.
Part of java.util package that implements the SortedSet interface.
Internally uses Red-Black tree to maintain sorting.
Does not allow null elements because sorting is based on comparison, which may cause NullPointerException.
Implements the NavigableSet interface and provides navigation methods like higher(), lower(), ceiling() and floor().
Not synchronized and can be made synchronized using Collections.synchronizedSet()

TreeSet hierarchy
Collection -> Set -> SortedSet -> NavigableSet -> TreeSet

A class to be used inside TreeSet has to implement Comparable or provide a Comparator to avoid 
ClassCastException at runtime.
Built-in classes like String and wrapper classes already implements Comparable.

If a class does not implement Comparable (e.g., StringBuffer), provide a Comparator.

import java.util.*;

class Geeks {

    public static void main(String[] args)
    {
        // Creating a TreeSet with a custom Comparator
        Set<StringBuffer> ts = new TreeSet<>(new Comparator<StringBuffer>() {
            @Override
            public int compare(StringBuffer sb1, StringBuffer sb2) {
                return sb1.toString().compareTo(sb2.toString());
            }
        });

        // Adding elements to the TreeSet
        ts.add(new StringBuffer("A"));
        ts.add(new StringBuffer("Z"));
        ts.add(new StringBuffer("L"));
        ts.add(new StringBuffer("B"));
        ts.add(new StringBuffer("O"));
        ts.add(new StringBuffer("1"));

        // Printing the elements
        System.out.println(ts); 
    }
}

// Output
[1, A, B, L, O, Z]

### EnumSet
EnumSet is highly specialized, high-performance Set implementation designed exclusively for Enum
types. It is represented internally as a bit vector, making it incredibly memory efficient and
fast. Operations like add, remove, and contains are O(1). Best for tracking states, permissions, 
or feature flags.
It uses BitVector as internal data structure.

import java.util.EnumSet;

enum Permission { READ, WRITE, EXECUTE }

public class PermissionsManager {
    public static void main(String[] args) {
        // Create a set with specific enum constants
        EnumSet<Permission> myPerms = EnumSet.of(Permission.READ, Permission.WRITE);
        
        // Check for membership
        if (myPerms.contains(Permission.READ)) {
            System.out.println("Read access granted.");
        }
        
        // Add a new permission
        myPerms.add(Permission.EXECUTE);
        System.out.println("Updated Permissions: " + myPerms);
    }
}

### CopyOnWriteArraySet
Thread-safe set where all mutative operations (like add or remove) creates a fresh copy of underlying 
array.
Perfect for read-heavy scenarios where we need thread-safety, but rarely modify the set. Iterators
provide a "snapshot" of the set, so we never encounter ConcurrentModificationException 
while traversing it
Practical Usage: Ideal for maintaining lists of event listeners or subscribers where the list changes 
infrequently but is notified/read by many threads.

Example:
import java.util.concurrent.CopyOnWriteArraySet;

public class EventPublisher {
    private final CopyOnWriteArraySet<String> subscribers = new CopyOnWriteArraySet<>();

    public void subscribe(String name) { subscribers.add(name); }

    public void notifyAll(String message) {
        // Safe to iterate even if another thread adds a subscriber during this loop
        for (String sub : subscribers) {
            System.out.println("Notifying " + sub + ": " + message);
        }
    }
}

CopyOnWriteArraySet is essentially a wrapper around a CopyOnWriteArrayList. It maintains an internal 
array. Whenever a modification occurs (like adding or removing an element), it creates a brand new 
copy of that entire array.

### ConcurrentSkipListSet
Thread-safe, sorted set based on a ConcurrentSkipListMap. 
How it works: A "skip list" is a probabilistic data structure that allows for fast search, insertion,
and deletion by maintaining multiple layers of linked lists, which "allow" the algorithm to "skip" 
over large sections of data.
Why Use It: Unlike CopyOnWriteArraySet, this does not copy the whole structure on every write, 
making it much more scalable for sets that are modified frequently. It keeps elements in sorted order
and provides O(log n) performance. 
Best for high-concurrency environments where we need a sorted set (eg. a real time leaderboard or
a priority-based task queue).
Uses ConcurrentSkipListMap as underlying data structure.


import java.util.concurrent.ConcurrentSkipListSet;

public class Leaderboard {
    private final ConcurrentSkipListSet<Integer> scores = new ConcurrentSkipListSet<>();

    public void addScore(int score) {
        scores.add(score); // Thread-safe, keeps list sorted
    }

    public void showTopScores() {
        System.out.println("Current scores in order: " + scores);
        System.out.println("Highest score: " + scores.last());
    }
}


## Map

### HashMap
Characteristics: No ordering guarantee, allows one null key and multiple null values, not thread-safe,
O(1) average time for get/put.
Default choice when we just need fast key lookups and don't care about order.

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Apples", 50);
        inventory.put("Bananas", 30);
        inventory.put("Cherries", 100);
        inventory.put(null, 0); // allowed

        System.out.println(inventory.get("Apples")); // 50
        inventory.merge("Apples", 10, Integer::sum);  // 60
        inventory.computeIfAbsent("Grapes", k -> 0);

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

Practical Usage: Caching, counting word frequencies, general purpose lookup tables,
configuration maps.

// Word frequency counter
Map<String, Integer> freq = new HashMap<>();
for (String word : "the quick brown fox the lazy dog the".split(" ")) {
    freq.merge(word, 1, Integer::sum);
}

### LinkedHashMap

Characteristics: Maintains insertion order (or access order if configured), slightly slower than 
HashMap due to internal doubly-linked list, allows null keys/values.
When To Use: When we need predictable insertion order, or want to build an LRU cache.

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        Map<String, String> capitals = new LinkedHashMap<>();
        capitals.put("India", "New Delhi");
        capitals.put("USA", "Washington DC");
        capitals.put("Japan", "Tokyo");

        // Iterates in insertion order
        capitals.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}

Multiple null keys allowed

Practical Usage- LRU cache

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    LRUCache(int capacity) {
        super(capacity, 0.75f, true); //true - access order
        this.capacity = capacity;
    }

    @Override 
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

public class Main {
    public static void main(String[] args) {
        // Usage
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache.get(1)); // moves 1 to end
        cache.put(4, "D");
        System.out.println(cache.keySet()); // [3, 1, 4]

    }
}

### TreeMap
Characteristics: Implements NavigableMap/SortedMap - keys are always sorted (natural order or via
Comparator). No null keys allowed (throws NullPointerException). O(log n) for get/put (Red-Black tree 
internally).

When to use: When we need sorted keys, range queries, or "closest match" lookups.

import java.util.TreeMap;
import java.util.Map;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<Integer, String> scores = new TreeMap<>();
        scores.put(85, "Alice");
        scores.put(92, "Bob");
        scores.put(76, "Charlie");
        scores.put(99, "Dave");

        System.out.println(scores); // sorted by key: {76=Charlie, 85=Alice, 92=Bob, 99=Dave}

        System.out.println(scores.firstKey());       // 76
        System.out.println(scores.lastKey());         // 99
        System.out.println(scores.higherKey(85));     // 92 (strictly greater)
        System.out.println(scores.floorKey(90));      // 85 (<= 90)
        System.out.println(scores.headMap(90));        // keys < 90
        System.out.println(scores.tailMap(90));        // keys >= 90

        // Custom comparator: descending order
        TreeMap<Integer, String> desc = new TreeMap<>((a, b) -> b - a);
        desc.putAll(scores);
        System.out.println(desc); // {99=Dave, 92=Bob, 85=Alice, 76=Charlie}
    }
}

Practical Usage: Leaderboards, range-based queries (e.g "find all events between two timestamps"),
auto sorted dictionaries.

### Hashtable
Characteristics: Legacy class, synchronized (thread-safe but slow), does not allow null keys or null
values.
When to Use: Rarely used 

import java.util.Hashtable;

public class HashtableExample {
    public static void main(String[] args) {
        Hashtable<String, Integer> table = new Hashtable<>();
        table.put("one", 1);
        table.put("two", 2);
        // table.put(null, 1); // throws NullPointerException

        System.out.println(table);
    }
}

Practical Usage: Legacy multi-threaded applications 

### ConcurrentHashMap
Characteristics: Thread-safe without locking the entire map (segment/bucket level locking),
high concurrency, no null key/values allowed, part of java.util.concurrent.
When to use: Multi-threaded environments needing a shared map with good performance.

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> counter = new ConcurrentHashMap<>();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.merge("count", 1, Integer::sum);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println(counter.get("count")); // 2000, safely
    }
}

Practical Usage: Shared caches, request counters, concurrent data aggregation in web servers/services.

// Atomic compute pattern - thread-safe increment without external locking
ConcurrentHashMap<String, Integer> visits = new ConcurrentHashMap<>();
visits.compute("home", (k, v) -> (v == null) ? 1 : v + 1);

### ConcurrentSkipListMap

Characteristics: Thread-safe and sorted (concurrent equivalent of TreeMap), Based on skip-list data 
structure, O(log n) operations, no null keys/values.
When To Use: Need both sorting and thread safety - eg. concurrent priority-based structure.

import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(5, "Five");
        map.put(1, "One");
        map.put(3, "Three");

        System.out.println(map); // {1=One, 3=Three, 5=Five} — always sorted

        System.out.println(map.firstEntry()); // 1=One
        System.out.println(map.ceilingKey(2)); // 3
    }
}

Practical Usage: Real time leaderboard dashboards, time series data structures accessed by 
multiple threads.

### IdentityHashMap
Characteristics: Uses reference equality (==) instead of equals() for comparing keys.
Rarely used, but has niche uses.

import java.util.IdentityHashMap;

public class IdentityHashMapExample {
    public static void main(String[] args) {
        IdentityHashMap<String, String> map = new IdentityHashMap<>();
        String a = new String("key");
        String b = new String("key");

        map.put(a, "Value A");
        map.put(b, "Value B"); // treated as a DIFFERENT key since a != b

        System.out.println(map.size()); // 2
    }
}

Practical Usage: Object graph traversal (e.g serialization frameworks, deep copy utilities),
where we need to track "have I already visited this exact object" rather than "an equal object".

// Cycle detection during object graph traversal
IdentityHashMap<Object, Boolean> visited = new IdentityHashMap<>();
void traverse(Object obj) {
    if (visited.containsKey(obj)) return; // already visited this exact instance
    visited.put(obj, true);
    // ... recurse into fields
}

### WeakHashMap

Characteristics: Keys are held via WeakReference. If a key has no other strong references, it is 
garbage collected and its entry auto removed. Useful for memory sensitive caches.

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        Map<Object, String> map = new WeakHashMap<>();
        Object key = new Object();
        map.put(key, "some cached data");

        System.out.println(map.size()); // 1

        key = null;             // remove strong reference
        System.gc();             // suggest GC
        Thread.sleep(500);

        System.out.println(map.size()); // likely 0 — entry was garbage collected
    }
}

Practical Usage: Caches where entries should automatically disappear once the key object is 
no longer used elsewhere (e.g listener registries, metadata caches tied to object lifecycles).

### EnumMap
Characteristics: Specialized, highly efficient map for enum keys only, internally backed by an 
array maintains natural enum order, no null keys.

import java.util.EnumMap;
import java.util.Map;

public class EnumMapExample {
    enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }

    public static void main(String[] args) {
        EnumMap<Day, String> schedule = new EnumMap<>(Day.class);
        schedule.put(Day.MON, "Gym");
        schedule.put(Day.WED, "Meeting");
        schedule.put(Day.FRI, "Deadline");

        // Iterates in enum declaration order, not insertion order
        for (Map.Entry<Day, String> entry : schedule.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

Practical Usage: State machines, day of week schedules, configuration keyed by fixed categories
(much faster and memory efficient than HashMap<Enum, .....>).

### Properties
Characteristics: Specialized for String key-value pairs, typically used for configuration files 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesExample {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.setProperty("db.url", "jdbc:mysql://localhost:3306/mydb");
        props.setProperty("db.user", "admin");

        // Save to file
        props.store(new java.io.FileOutputStream("config.properties"), "DB Config");

        // Load from file
        Properties loaded = new Properties();
        loaded.load(new FileInputStream("config.properties"));
        System.out.println(loaded.getProperty("db.url"));
    }
}

Practical usage: Application configuration, .properties resource bundles, environment/deployment settings.

## Summary Table
| Implementation | Ordering | Thread-Safe | Nulls Allowed | Performance | Best For | 
| -------- | --------- | ------- | -------- | ------- | ------- |
| HashMap | None | No | 1 null key many null values | O(1) avg | General purpose | 
| LinkedHashMap | Insertion/access order | No | Yes | O(1) avg | Predictable Iteration, LRU cache | 
| TreeMap | Sorted | No | No null keys | O(log n) | Sorted data, range queries | 
| Hashtable | None | Yes (full lock) | No | O(1) avg, slower | Legacy code, only | 
| ConcurrentHashMap | None | Yes (fine grained) | No | O(1) avg | High-concurrency apps | 
| ConcurrentSkipListMap | Sorted | Yes | No | O(log n) | Concurrent + Sorted | 
| IdentityHashMap | None | No | Yes | O(1) avg | Reference-based comparison | 
| WeakHashMap | None | No | Yes | O(1) avg | GC-sensitive caches | 
| EnumMap | Enum order | No | No null keys | O(1), array backed | Enum-keyed data | 
| Properties | None | Yes (Inherits Hashtable)  | No | O(1) avg | Config files | 



