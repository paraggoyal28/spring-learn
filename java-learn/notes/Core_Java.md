## Core Java

### String

Strings are immutable, means once a String object is created, its contents cannot be changed.

### String s = "Java";
s.concat("Programming");
System.out.println(s);
// Java

concat() creates a new String, not modifies original one.

### Why Strings are immutable 
1. Security - Strings are used for passwords, file paths, URLs, etc.
2. String Pool - Immutable strings can be safely shared
3. Thread safety - Immutable objects are inherently thread safe.
4. Hashing - Strings can be safely used as a HashMap key because its content cannot be changed

### String Pool is a special memory area where JVM stores shared String literals.

String a = "Java";
String b = "Java"; 
Both point to the same string pool object

Using new creates a new String object in heap memory area.

### == vs .equals

== compares references for objects
.equals() compares content for Strings

String a = new String("Java");
String b = new String("Java");

System.out.println(a == b); // false
System.out.println(a.equals(b));  // true

### compareTo()
Compares two strings lexicographically

Returns
0 - equal
negative - first string comes before second
positive - first string comes after second

"Apple".compareTo("Banana"); // negative
"Banana".compareTo("Apple"); // positive
"Java".compareTo("Java"); // 0

### compareToIgnoreCase()
compares ignoring the case

"java".compareToIgnoreCase("Java"); // 0 

### concat()

Combines two strings

String s = "Hello";
String result = s.concat(" World");
System.out.println(result); // Hello World

### substring()

Extracts part of a string
String s = "JavaProgramming";

System.out.println(s.substring(4)); // Programming
System.out.println(s.substring(0, 4)); // Java

Start index is inclusive, end index is exclusive

### charAt()

Returns the character at given index

String s = "Java";
System.out.println(s.charAt(0)); // J

### indexOf() 

Returns the first occurence of a character/String

String s = "Java Programming"; 

System.out.println(s.indexOf("Prog")); // 5

Returns -1 if not found

### lastIndexOf()

Returns the last occurrence

String s = "Java";
System.out.println(s.lastIndexOf('a')); // 3

### contains()

Check whether a String contains a particular sequence

"Java Programming".contains("Program"); // true

Returns boolean

### startsWith()

Check whether a string starts with a particular sequence

"Java Programming".startsWith("Java"); // true

### endsWith()

Check whehter a string ends with a particular sequencer

"Java Programming".endsWith("ing"); // true

### replace()

Replaces characters or sequences

String s = "Java Java";
System.out.println(s.replace("Java", "Python")); // Python Python

### replaceAll()

Replaces matches using a regular expression syntax
String s = "Java123";
System.out.println(s.replaceAll("\\d", "")); // Java

Important difference between replace and replaceAll

replace -> literal replacement
replaceAll -> regex replacement

### split()

Splits a string based on a delimiter/regex and returns a String array
String s = "Java,Python,Ruby";
String[] arr = s.split(",");
System.out.println(arr[0]); // Java

### trim()

Removes leading and trailing whitespaces 
String s = " Java ";
System.out.println(s.trim()); // Java

### strip()
Introduced in Java 11.
Removes leading and trailing whitespaces Unicode characters
String s = " Java ";
System.out.println(s.strip()); // Java

Important:
trim() -> older, limited whitespace handling
strip() -> newer Unicode-aware

### isEmpty()
"".isEmpty(); // true
" ".isEmpty(); // false

### isBlank() 
"".isBlank(); // true
"  ".isBlank(); // true
"Java".isBlank(); // false

Important:
isEmpty() -> length == 0
isBlank() -> empty or only whitespaces

### join()

Joins multiple strings using a delimiter

String result = String.join("-", "Java", "Spring", "Javascript");
System.out.println(result); // Java-Spring-Javascript

### String.valueOf()

Converts value into its String rep

int n = 100;
String s = String.valueOf(n);
System.out.println(s); // "100"

Commonly used to convert primitive values into String

Primitive->String
int n = 100;
String s1 = String.valueOf(n);
String s2 = Integer.toString(n);

String->Primitive
String s = "10";
int n = Integer.parseInt(s);
double d = Double.parseDouble("10.93");
boolean b = Boolean.parseBoolean("true"); 

### Most important interview questions

String->immutable
"Java"->String pool
new String("Java")-> Heap memory
== -> Reference comparison
.equals() -> Content comparison
replace() -> literal replacement
replaceAll() -> Regex replacement
trim() -> traditional whitespace removal
strip() -> unicode-based whitespace removal
isEmpty() -> empty only
isBlank() -> empty or having only whitespaces

### StringBuilder

Mutable sequence of characters used when frequent String modifications are required

StringBuilder sb = new StringBuilder("Java");
sb.append(" Programming");
System.out.println(sb.toString()); // Java Programming

### insert() 

insert content at specific position

StringBuilder sb = new StringBuilder("Jva");
sb.insert(1, "a");
System.out.println(sb);

// Java

### delete() 

Removes characters from start to end-1
StringBuilder sb = new StringBuilder("Javc");
sb.delete(1, 3);
System.out.println(sb); // Jc

### reverse() 

StringBuilder sb = new StringBuilder("Java");
sb.reverse();
System.out.println(sb);
// avaJ

### capacity

StringBuilder sb = new StringBuilder();
System.out.println(sb.capacity()); // 16

StringBuilder sb = new StringBuilder(50);
System.out.println(sb.capacity()); // 50

String -> Immutable
StringBuilder -> Mutable

StringBuilder is preferred over repeated String modifications 

### StringBuffer

StringBuffer is mutable sequence of characters, similar to StringBuilder

StringBuffer sb = new StringBuffer("Java");
sb.append(" Programming");
System.out.println(sb); // Java Programming

### StringBuilder vs StringBuffer

| StringBuilder | StringBuffer | 
| ------ | ----- |
| Mutable | Mutable | 
| Not synchronized | Synchronized |
| Not Thread-safe | Thread-Safe | 
| Generally faster | Slower |
| Java 5+ | Older versions |

Use StringBuilder when thread safety is not required because it generally provides
better performance. Use StringBuffer when multiple threads need synchronized access 
to the same character sequence

String -> Immutable
StringBuilder -> Mutable + Fast performance
StringBuffer -> Mutable + thread safe

### String vs StringBuilder

| String | StringBuilder | 
| ---- | ---- | 
| Immutable | Mutable |
| Modifications creates new object | Modifies existing object | 
| Slower for many modifications | Faster for many modifications | 
| Thread-safe due to immutability | Not thread-safe |
 
String s = "Java";

s = s + " Programming";
// New String created

StringBuilder sb = new StringBuilder("Java");

sb.append(" Programming");
// Same object modified

### StringBuilder vs StringBuffer

| StringBuilder | StringBuffer |
| ----- | ---- | 
| Mutable | Mutable |
| Not Thread safe | Thread safe |
| Faster | Slower |
| Not Synchronized | Synchronized | 

StringBuilder -> Used when thread-safety is not required
StringBuffer -> Used when synchronized access is required
