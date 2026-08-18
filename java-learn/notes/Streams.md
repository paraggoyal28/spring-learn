# Streams

## Short-Circuiting Trap
List<String> list = Arrays.asList("apple", "banana", "cherry", "date");
boolean result = list.stream().peek(System.out::print).anyMatch(s -> s.startsWith("b"));
System.out.print(" -> " + result);

Output:
applebanana -> true

Explanation: 
* anyMatch is a short circuiting terminal operation. It stops processing the stream the exact moment it finds an element that matches the predicate.
* Elements are processed one by one down the pipeline
* First, "apple" passes through the peek (prints apple), but fails anyMatch
* Next, "banana" passes through the peek (prints banana), and satisfies anyMatch
* Because anyMatch is now guaranteed to be true, evaluation stops immediately. "cherry" and "date" are not processed.

## Stream Reused Exception

List<String> names = Arrays.asList("Ana", "Bob", "Charlie");
Stream<String> stream = names.stream().filter(s -> s.length() > 2);

long count = stream.count();
System.out.print("Count: " + count);

List<String> collected = stream.map(String::toUpperCase).collect(Collectors.toList());
System.out.print(" Collected: " + collected.size());

Output:
It prints Count: 3 and then throws an java.lang.IllegalStateException: stream has already been operated upon or closed.

Explanation:
* A Java stream can only be operated upon exactly once.
* The moment a terminal operator like count(), collect(), forEach() or reduce() is invoked, the stream is consumed and closed.
* Attempting to reuse the same stream instance (stream.map(....)) throws an IllegalStateException at runtime. To fix this, 
a new stream must be opened from the source collection.

## Lazy Evaluation Illusion


List<Integer> numbers = Arrays.asList(1, 2, 3);
Stream<Integer> stream = numbers.stream()
    .peek(System.out::print);

System.out.print("Done");


Output: 
Done

Explanation:
* Streams are lazily evaluated. Intermediate operations (like peek, map, filter) do not execute until a terminal operation is attached to the end of the pipeline.
* Because the code lacks a terminal operation (such as .collect(), .count() or .forEach()), the stream pipeline never triggers. The peek operation is completely ignored, printing only "Done".

## Side Effects and State Modification

List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
list.stream()
    .filter(n -> n % 2 == 0)
    .forEach(n -> list.remove(n)); 

System.out.println(list);


Output:
Throws a ConcurrentModificationException at runtime.

Explanation:
* While executing a stream pipeline, we must not modify the underlying backing data source (the list itself). This is known as interference.
* The structural modification of the array via list.remove(n) inside the terminal operation disrupts the stream iterator, triggering a ConcurrentModificationException.

## map Vs flatMap Dimensionality 

What is the exact output of below code ? 


List<List<Integer>> complexStructure = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4)
);

complexStructure.stream()
    .map(list -> list.stream().map(n -> n * 2))
    .forEach(System.out::print);


Output: A sequence of object memory addresses/references (e.g., java.util.stream.ReferencePipeline$3@5acf98e4...) instead of numbers.

Explanation:
* The .map() operation changes the type of elements but keeps a 1:1 relationship because the mapper function returns 
list.stream().map(....), the resulting stream is a stream of streams ()