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

1. Filter Even Numbers

From a list of integers, return only even numbers

List<Integer> result = numbers.stream()
                              .filter(n -> n%2 == 0)
                              .toList();

// Input: [1, 2, 3, 4, 5, 6]
// Output: [2, 4, 6]

2. Square of each number

Return a list containing the squares of each number

List<Integer> result = numbers.stream()
                              .map(n -> n * n)
                              .toList();

// Input: [1, 2, 3, 4]
// Output: [1, 4, 9, 16]

3. Find first non-repeating character in String

Return the first character that only appears once

Optional<Character> result = str.chars()
                                .mapToObj(c -> (char) c)
                                .filter(ch -> str.indexOf(ch) == str.lastIndexOf(ch))
                                .findFirst();
                        
// Input: "swiss"
// Output: Optional[w]

4. Count occurrences of each element

Return a map with element as the key and its frequency as value

Map<Integer, Long> freq = numbers.stream()      
                                 .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
            
// Input: [1, 2, 2, 3, 3, 3]
// Output: [1=1, 2=2, 3=3]

5. Sort list of strings by length

List<String> result = names.stream()
                           .sorted(Comparator.comparingInt(String::length))
                           .toList();
                        
// Input: ["bob", "alex", "ram", "java"]
// Output: ["bob", "ram", "alex", "java"]

6. Sum of all numbers

int sum = numbers.stream()
                 .mapToInt(Integer::intValue)
                 .sum();
            
// Input: [1, 2, 3, 4]
// Output: 10

7. Remove duplicates from a list

List<Integer> dedup = numbers.stream()
                            .distinct()
                            .toList();
                    
// Input: [1, 1, 2, 2, 2, 3, 3, 3]
// Output: [1, 2, 3]

8. Find Maximum and Minimum

Find the max and min value from a list

Optional<Integer> maxValue = numbers.stream().max(Integer::compare);
Optional<Integer> minValue = numbers.stream().min(Integer::compare);

// Input: [1, 4, 8, 10]
// MaxValue: 10
// MinValue: 1

9. Convert a list of String to uppercase

Convert all strings in a list to uppercase

List<String> upperCaseStrings = names.stream()
                                    .map(String::toUpperCase)
                                    .toList();
                            
// Input: ["bob", "alex"]
// Output: ["BOB", "ALEX"]

10. Partition list into even and odd numbers

Map<Boolean, List<Integer>> result = numbers.stream()
                                            .collect(Collectors.partitioningBy(n -> n%2 == 0));
                                        
// Input: [1, 2, 3, 4, 5]
// Output: {true=[2, 4], false=[1, 3, 5]}

11. Join List of Strings with Comma

String result = names.stream()
                    .collect(Collectors.joining(", "));

// Input: ["a", "b", "c"]
// Output: "a, b, c"

12. Group Strings by first character

Map<Character, List<String>> result = names.stream()
                                            .collect(Collectors.groupingBy(s -> s.charAt(0)));

// Input: ["apple", "act", "bob", "bat"]
// Output: {"a": ["apple", "act"], "b": ["bob", "bat"]}

13. Find second highest number

Optional<Integer> secondHighestNumber = numbers.stream()
                                                .distinct()
                                                .sorted(Comparator.reverseOrder())
                                                .skip(1)
                                                .findFirst();

// Input: [10, 5, 20, 3, 80]
// Output: 20

14. Check if anyMatch, allMatch, or noneMatch

Any number > 10
All number > 0
No number < 0


boolean any = numbers.stream().anyMatch(n -> n > 10);
boolean all = numbers.stream().allMatch(n -> n > 0);
boolean none = numbers.stream().noneMatch(n -> n < 0);

