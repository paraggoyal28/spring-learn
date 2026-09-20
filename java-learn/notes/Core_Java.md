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

## Constructor Chaining

When a child object is created, the parent constructor executes first, then the 
child constructor

class Animal {
    Animal() {
        System.out.println("Animal Class");
    }
}

class Dog extends Animal {
    Dog() {
        System.out.println("Dog Class");
    }
}

public class Main {
    public static void main(String args[]) {
        Dog d = new Dog();
    }
}

// Output:
Animal Class
Dog Class

super() is automatically called inside child constructor as the first line

## Compile-time Polymorphism

Resolved by compiler

Method Overloading
Multiple methods with the same name but different parameter lists.

class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

Important: Chaning only the return type does not create overloading

## Runtime Polymorphism
Resolved at runtime using method overriding

Method Overriding

class Animal {
    void sound() {
        System.out.println("Animal Sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}

## Dynamic Method Dispatch

When a parent reference refers to a child object, the overridden method is selected.

Animal a = new Dog();
a.sound();

Output:
Bark

Actual object is Dog, so Dog's method is executed

## Upcasting

Converting a child reference to parent reference.
Automatic

Dog d = new Dog();
Animal a = d;

or 
Animal a = new Dog();

## Downcasting

Converting a parent reference back to child reference
Requires explicit casting

Animal a = new Dog();
Dog d = (Dog) a;
d.sound();

Important: Downcasting is safe only when the actual object is an instance of that child class

Animal a = new Animal();
Dog d = (Dog) a; // ClassCastException

## Methods are overridden while instance variables are hidden

class Animal {
    String name = "Generic Animal";

    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    String name = "Dog"; // Hides the parent variable

    @Override
    void sound() {
        System.out.println("Bark");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();

        a.sound(); // Bark (Uses actual object's method)
 
        System.out.println(a.name); // Generic Animal (Uses reference object's variable)
    }
}

Methods (Overridden)
Variables (Hidden): When a subclass declares a variable with the same name as one in
the parent class, it hides it rather than overriding it, the compiler binds myPet.name
to the Animal class 
Static Methods (Hidden): Reference type method only called

## Quick Interview Summary

Overloading -> Compile-time
Overriding -> Run-time
Dynamic Dispatch -> Overridden method decided at runtime
Upcasting -> Child->Parent
Downcasting -> Parent->Child

## Abstraction

Hiding implementation details and exposing only the required functionality

Abstract Class
A class declared with abstract keyword. Has both abstract and instance methods.

abstract class Animal {
    abstract void sound();

    void eat() {
        System.out.println("Eating");
    }
}

// It cannot be instantiated directly:
Animal a = new Animal(); 
// Error

The child class must implement the abstract method otherwise the child class too is 
abstract

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}

## Summary for Instance, Static and Final methods

| Parent Method Modifier | Can subclass use same name & signature ?  | What is it called ? | Resolution Time |
| --- | ---- | ----- | ----- |
| Instance (Regular) | Yes | Method Overriding | Runtime (Polymorphism) | 
| Static | Yes | Method Hiding | Compile Time (Non-Polymorphic) | 
| Final | No (Compile Error) | N/A | N/A | 

Trying to write an instance method in a subclass that matches a static method in 
the parent class results in a compile time error

Trying to write a static method in a subclass that matches an instance method in
the parent class results in a compile time error

## Functional Interfaces

Functional Interface has only one abstract method

@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

Can be used with lambda expressions

Calculator c = (a, b) -> a  * b;

Examples: Runnable, Comparator, Predicate, Supplier, Consumer

## Multiple Inheritance using Interfaces

interface A {
    default void show() {
        System.out.println("Inside Interface A");
    }
}

interface B {
    default void show() {
        System.out.println("Inside Interface B");
    }
}

class C implements A, B {
    public void show() {
        A.super.show();
    }
}

Class -> extends -> one class
Class -> implements -> multiple interfaces

## Important OOP Comparison

| Abstract Class | Interface | 
| ---- | ---- | 
| abstract class | interface |
| can have constructors | cannot have constructors |
| can have instance variables | fields are public static final by default |
| can have abstract + concrete methods | can have abstract, default, and static methods |
| class extends one abstract class | class can implement multiple interfaces | 
| extends | implements |

## Overloading Vs Overriding

| Overloading | Overriding | 
| --- | ---- |
| Same method name | Same method signature | 
| Different parameters | Same parameters |
| Compile-time polymorphism | Runtime polymorphism | 
| Usually within the same class | Requires inheritance | 
| Return type alone cannot overload | Return type must be compatible | 

## Encapsulation Vs Abstraction

| Encapsulation | Abstraction |
| Protects/hides data | Hides implementation details | 
| private + setters/getters | abstract classes/interfaces | 
| Focuses on how data is accessed | Focuses on what is exposed | 

Encapsulation -> Data hiding
Abstraction -> Implementation hiding

## Inheritance Vs Composition

Inheritance
class Dog extends Animal
Is-A relationship

Composition
class Car {
    Engine engine = new Engine();
}
Has-A relationship

Interview Point: Prefer composition when we want flexibility and loose coupling 
rather than unnecessary inheritance

## Static vs Instance

| Static | Instance |
| ---- | --- |
| Belongs to class | Belongs to object |
| Shared among all objects | Each object has its own copy |
| Can access without object | Usually requires object | 
| className.method() | object.method() |

class Student {
    static String college = "XIT";
    String name;
}

college -> static/shared
name -> instance/separate for each object

## Final class vs Abstract class

| Final class | Abstract Class |
| --- | ---- | 
| Cannot be inherited | Designed to be inherited | 
| Cannot have subclass | Can be inherited and have subclass | 
| Can be instantiated | Cannot be instantiated | 
| Used to prevent inheritance | Used to provide abstraction |

final class A {
    
}

class B extends A {
    // Error
}

abstract class A {

}

A a = new A(); // Error

## Key Points
final class     → "Don't inherit me"
abstract class  → "You must inherit/implement me to create a concrete object"


