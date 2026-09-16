# Basic Java Interview Questions

## Q1. What is the output of below code

String a = "Hello"; \
a.concat("World");  \
System.out.println(a); 

### Output:
Hello

### Explanation:
Because String is immutable in java when we did a.concat("World"), it creates a new String "HelloWorld" in General Heap area.


## Q2. Will the output of below code

import java.util.*;

class A {
  public void display() {
    System.out.println("Hello, this is class A");
  }
}

class B extends A {
  protected void display() {
    System.out.println("Hello, this is class B");
  }
}


public class Main {

    public static void main(String[] args) {
      B obj = new B();
      obj.display();
      
    }
}

### Output:
Main.java:10: error: display() in B cannot override display() in A
  protected void display() {
                 ^
  attempting to assign weaker access privileges; was public
1 error

### Explanation
The subclass overriding the superclass method should have the broader access priviledges than the superclass method
for eg. if protected is in super class, then public in subclass. 


## Q3. What is the output of below code

class A {
  static void display() {
    System.out.println("Hello, this is class A");
  }
}

class B extends A {
  static void display() {
    System.out.println("Hello, this is class B");
  }
}


public class Main {

    public static void main(String[] args) {
      A a = new B();
      a.display();
    }
}

### Output:
Hello, this is class A

### Explanation
This concept is called Method Hiding.
Because display() is static, the compiler looks at the reference type (class A). It says, "Okay, this is a class A reference, so I am going to call the display() method that belongs to the class A." It does not care that the object created is of class B.

## Q4. What is the output of below code

class A {
  public void display() {
    System.out.println("Hello, this is class A");
  }
}

class B extends A {
   public void display() {
    System.out.println("Hello, this is class B");
  }
}


public class Main {

    public static void main(String[] args) {
      A a = new B();
      a.display();
    }
}

### Output:
Hello, this is class B

### Explanation
This concept is called method overriding. JVM decides at runtime which class method to call. Here B's method is called. This is called dynamic method dispatch or runtime polymorphism.


## Difference between compile time polymorphism and Runtime Polymorphism
1. Method Hiding = Compile-Time Polymorphism
When you call a static method, the Java compiler looks at the reference type (the "label" on the variable) at the time you compile your code.

Why it's Compile-Time: The compiler essentially replaces the method call with the specific class method address during the compilation phase. It does not need to know what kind of object is in that variable at runtime; it only cares about the type of the variable itself.

Key Characteristic: The decision is "locked in" before the program even runs.

2. Method Overriding = Runtime Polymorphism
When you call an instance (non-static) method, the Java Virtual Machine (JVM) looks at the actual object stored in memory at the exact moment the line is executed.

Why it's Runtime: The program doesn't know which version of the method to run until it actually "looks" inside the memory to see if the object is an instance of Class A or Class B. This is handled by the JVM's virtual method table (vtable).

Key Characteristic: The decision is "deferred" until the program is actively running.

## Q5. What is the output for below code:
String abc = "Java";
String pqr = "Ja" +  "va"; 
System.out.println(abc == pqr);

### Output: 
true

Because "Ja" and "va" both are string literals, so the compiler folds it at the compile time into a single literal "Java". That
literal is internal, so abc and pqr refer to the same pooled object. Therefore abc == pqr is true.

## Q6. What is the output for below code:
String abc = "Java";
String pqr = "Ja";
String xyz = pqr + "va";
System.out.println(abc == xyz);

### Output:
false

Because pqr is a variable. The string xyz is evaluated at runtime. That typically creates a new String object, so xyz is 
different reference from the internal reference "Java" stored in abc. Therefore abc == xyz is false.



## Q7. What is wrong with below code

package p1;

public class Parent {
  public void display() {
    System.out.println("Hello, This is a parent class");
  }
}

package p2;

class Child extends Parent {

  int cnt = 0;

  void display() {
    System.out.println("Hello, This is a child class");
  }

  static void increment() {
    cnt++;
  }
}

### Three problems with above question:
1. Parent is declared in package p1 so using it in package p2 requires us to import p1.Parent;
2. cnt is a non-static variable, it cannot be used in a static method increment
3. overridden display method in the child class has a lower access specifier than the display method in the parent class.

Correct version will be:

package p2;

import p1.Parent;

class Child extends Parent {

  int cnt = 0;

  @Override
  public void display() {
    System.out.println("Hello, This is a child class");
  }

  void increment() {
    cnt++;
  }
}

Or if increment needs to be stayed static then 

static void increment(Child child) {
    child.cnt++;
}

## Q8. What is the output for below code

Function<Integer, Integer> doubleIt = x -> x * 2;
Function<Integer, Integer> addTen = x -> x + 10;
Function<Integer, Integer> result = doubleIt.compose(addTen);
Function<Integer, Integer> result2 = doubleIt.andThen(addThen);

System.out.println(result.apply(5)); // 30
System.out.println(result2.apply(5)); // 20

## Q9. What is the output for below code:
String abc = "Java";
final String pqr = "Ja";
String xyz = pqr + "va";
System.out.println(abc == xyz);

### Output:
true

The output is true because pqr is a final variable initialized with a compile-time constant.
pqr is a constant variable because:
has all of these properties:

1. It is final
2. Its type is String
3. It is initialized with a compile-time constant expression: "Ja"

So this expression 
pqr + "va" 
is also treated as a compile-time constant expression.

The compiler effectively changes this:
String xyz = pqr + "va";
into
String xyz = "Java";

Now both variables refer to the same string literal from the string pool:
String abc = "Java";
String xyz = "Java";

So:
abc == xyz
checks whether both references point to the same object. In this case, they do, so the output is:
True

## Q9: What is the output of the below program

class Test {

  static int cnt = 0;

  Test() {
    cnt += 1;
  }
}

public class Main {
  public static void main(String[] args) {

    Test test1 = new Test();
    Test test2 = new Test();
    Test test3 = new Test();

    System.out.println(test1.count);
    System.out.println(test2.count);
    System.out.println(test3.count);

  }
}

### Output:
3
3
3

### Explanation:
static is a shared variable so after three Test instances are initialized the count becomes 3.
Therefore all the instances count variable will be 3.


## Q10: What is the output of the below program

class Test {

  int cnt = 0;

  Test() {
    cnt += 1;
  }
}

public class Main {
  public static void main(String[] args) {

    Test test1 = new Test();
    Test test2 = new Test();
    Test test3 = new Test();

    System.out.println(test1.count);
    System.out.println(test2.count);
    System.out.println(test3.count);

  }
}

### Output:
1
1
1

### Explanation:
instance variable are specific to the class instance. So the count variable is 1 for each of the Test instance.

## Q11: Method overloading with compile-time overload resolution / most specific method selection.


class Main {
    private static void print(Object obj) {
        System.out.println("Object");
    }

    private static void print(String str) {
        System.out.println("String");
    }

    public static void main(String args[]) {
        new Main().print(null);
    }
}

### Output
String

More specifically, this example demonstrates overload resolution: Java decides which overloaded method to call at compile time based on the argument type.
When you pass null, both methods can accept it, but String is more specific than Object, so Java chooses: String instead of Object

## Q12: Method overloading with compile-time overload resolution / most specific method selection.


class Main {
    private static void print(Object obj) {
        System.out.println("Object");
    }

    private static void print(String str) {
        System.out.println("String");
    }

    private static void print(Integer str) {
        System.out.println("Integer");
    }

    public static void main(String args[]) {
        new Main().print(null);
    }
}

### Output
Compile Time Error
Main.java:17: error: reference to print is ambiguous
        new Main().print(null);
                  ^
  both method print(String) in Main and method print(Integer) in Main match

### Q13: 

import java.util.*;

class Main {
    private static void print(Object obj) {
        System.out.println("Object");
    }

    private static void print(long str) {
        System.out.println("long");
    }

    private static void print(Integer str) {
        System.out.println("Integer");
    }

    public static void main(String args[]) {
        new Main().print(10);
    }
}

### Output:
long

### Explanation:
Integer 10 is assigned to long because it is primitive type
print(Object obj)   // needs boxing int -> Integer, then upcast to Object
print(long str)     // needs widening int -> long
print(Integer str)  // needs boxing int -> Integer

Java overload resolution prefers conversions in this order:
1. Exact match
2. Primitive widening, like int -> long
3. Boxing, like int -> Integer
4. Varargs


### Q14: 

import java.util.*;

class Main {
    private static void print(Object obj) {
        System.out.println("Object");
    }

    private static void print(Long str) {
        System.out.println("Long");
    }

    private static void print(Integer str) {
        System.out.println("Integer");
    }

    public static void main(String args[]) {
        new Main().print(10);
    }
}

### Output:
Integer

### Explanation:
Now Integer is more near to int than the Long Wrapper class.
So it box to Integer


### Q15. What is the output ? 

public final class Example04 {
    public static void main(String[] args) {
        long x = (1 << 24) + 1;
        if (x != (x += 0.0f)) {
            System.out.println("What??");
        } else {
            System.out.println("OK!");
        }
        System.out.println("x = " + x + "; ... (x += 0.0f) = " + (x += 0.0f));
}   }

## Output
738 ms
What??
x = 16777216; ... (x += 0.0f) = 16777216

public final class Main {
    public static void main(String[] args) {
        long x = (1 << 23) + 1;
        if (x != (x += 0.0f)) {
            System.out.println("What??");
        } else {
            System.out.println("OK!");
        }
        System.out.println("x = " + x + "; ... (x += 0.0f) = " + (x += 0.0f));
}   }

## output
712 ms
OK!
x = 8388609; ... (x += 0.0f) = 8388609

### Explanation 
The key is that x += 0.0f performs the addition using 32-bit float arithmetic, even though x is a long.
x += 0.0f; 
is basically
x = (long) ((float) x + 0.0f);

The explicit cast to float is where precision may be lost.

First program: (1 << 24) + 1
This produces:
x = 16777217
A Java float has 24 bits of integer precision: 23 stored fraction bits plus one implicit leading bit. It can represent every integer exactly only through:

2^24 = 16777216

It cannot represent 16777217. Converting it to float rounds it to:
16777216.0f
Therefore 
x += 0.0f;
returns
16777216.0f

Java evaluates operands from left to right:

The left x is read as 16777217.
The right expression changes x to 16777216.
Java compares:

16777217 != 16777216
That is true, so it prints:
What?? 


Second Program: (1 << 23) + 1

long x = (1 << 23) + 1;
produces
8388609

THis value is below 2^24, so it is exactly representable as float

Thus:
x += 0.0f;
does not change 8388609 → 8388609.0f → 8388609

The comparison is therefore:
8388609 != 8388609
which is false, so the program prints:

OK!
x = 8388609; ... (x += 0.0f) = 8388609

## Checked Vs Unchecked exceptions

### Checked exception
Checked exception must be either handled using try-catch or declared using throws:
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("data.txt");
        } catch (IOException e) {
            System.out.println("File could not be opened.");
        }
    }
}

Alternatively:
public static void readFile() throws IOException {
    FileReader reader = new FileReader("data.txt");
}

Without try-catch or throws, the code will not compile.

Checked exceptions commonly represent situations outside the program’s direct control, such as:

* A file not existing
* A database connection failing
* A network operation failing

### Unchecked Exception 

An unchecked exception does not need to be caught or declared.

public class Main {
    public static void main(String[] args) {
        int result = 10 / 0;
    }
}

This compiles successfully, but at runtime it throws:

ArithmeticException

Another example:
String name = null;
System.out.println(name.length());

This throws a NullPointerException.

Unchecked exceptions usually indicate:

Invalid arguments
Incorrect assumptions
Logical programming errors
Improper object state

Exception hierarchy

Throwable
├── Error
└── Exception
    ├── IOException
    ├── SQLException
    └── RuntimeException
        ├── NullPointerException
        ├── ArithmeticException
        ├── IllegalArgumentException
        └── IndexOutOfBoundsException

Exceptions under RuntimeException are unchecked. Other subclasses of Exception are generally checked.

A custom checked exception extends Exception:

class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}

A custom unchecked exception extends RuntimeException:

class InvalidAccountException extends RuntimeException {
    public InvalidAccountException(String message) {
        super(message);
    }
}

### Different Access Specifiers

Access Modifier.      Within Same Class.   Within Same Package.    Outside Package (Subclass Only)   Everywhere (World)
private                  Yes                    No                          No                           No
Default (No keyword).    Yes                    Yes                         No                           No
protected                Yes                    Yes                         Yes                          No
public                   Yes                    Yes                         Yes                          Yes


## Minimize the accessibility of classes and members

Make each class or member as inaccessible as possible.


### Checked Vs Unchecked Exception

Checked
IOException
FileNotFoundException
SQLException

Unchecked Exceptions
NullPointerException
ArithmeticException
ArrayIndexOutOfBoundsException


### What is immutable class 
1. Declare the class as final
2. Make all fields as private and final
private - restricts direct access from outside the class
final - ensures that each field can be assigned a value only once 
3. Donot provide setter methods
4. Initialize via constructor
5. Handle mutable objects via Deep copy

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Declare class as final
public final class Team {
  private final String teamName;
  private final List<String> members; // Mutable object field

  public Team(String teamName, List<String> members) {
    this.teamName = teamName;


    // DEFENSIVE COPY in constructor
    // We create a brand new ArrayList using the passed list
    // This ensures that if the caller modifies their original list outside
    // it won't affect our internal state
    this.members = new ArrayList<>(members); 
  }

  public String getTeamName() {
    return teamName;
  }

  public List<String> getMembers() {
    return Collections.unmodifiableList(members);
  }

  @Override
  public String toString() {
    return "Team Name: " + teamName + ", Members: " + members ; 
  }
}

class Main {
  public static void main(String[] args) {
    List<String> originalList = new ArrayList<>();
    originalList.add("Alice");
    originalList.add("Bob");

    Team team = new Team("Engineering", originalList);
    System.out.println("Before modification: " + team);

    originalList.add("Charlie");
    System.out.println("After modification: " + team);

    try {
      team.getMembers().add("David");
    } catch (UnsupportedOperationException ex) {
      System.out.println("Blocked! Cannot modify an immutable class's list: " + ex);
    }
  }
}

Why this is critical for immutability ? 
1. The Constructor Trap (Without Defensive Copy): If we just assign this.members = members; anyone with a reference to 
the originalList can alter the contents of the list after the Team object is created, effectively changing the internal state
of our "immutable" object.

2. The Getter Trap (Without unmodifiable view): If we just return return this.members, a caller could execute team.getMembers().clear() or .add() by passing the private field restriction and modifying our object. Using Collections.unmodifiableList() throws 
an UnsupportedOperationException if anyone attempts to alter it.

### What is the difference between Strongly Typed and Weakly Typed Languages

#### Weakly Typed Languages

No strict enforcement on the value assigned to a variable.
For eg. let x = 23;
Now x can be assigned a string as well, like x = "Hello".
The language itself interprets the type of the variable based on the value provided.
Can led to issues.
Simpler to use
Eg. Javascript, Python

#### Strongly Typed Language

Strict enforcement on the value assigned to a variable.
For eg. String x = "Hello";
Now x cannot be assigned x = 23, as it is of type String.
Prevent issues like doing mathematical operations on String type variables.
Is a bit difficult to use, because we need to memorize all types of variables before using them
but can prevent Runtime exception, not checked during writing code.
Eg. C++, Java, C

## equals vs hashcode contract

In Java, equals() defines when two objects mean "the same value", hashCode() produces an integer used to 
quickly group objects in hash based collections such as HashMap and HashSet.

The core contract is:
1. If a.equals(b) is true, then a.hashCode() == b.hashCode() must also be true.
2. If hash codes are equal, objects do not have to be equal - collisions are allowed.
3. Both methods use the same fields.
4. Fields used in equality should not change while an object is used as a HashMap key.

Example:

import java.util.Objects;

public final class Person {
  private final String id;
  private final String name;

  public Person(String id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceOf Person other)) {
      return false;
    }

    return Objects.equals(id, other.id) && Objects.equals(name, other.name);
  } 

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}

Output:
Map<Person, String> map = new HashMap<>();

map.put(new Person("42", "Asha"), "Engineer");

String role = map.get(new Person("42", "Asha"));
// "Engineer"

Internally, HashMap:
1. Calls hashCode() on the lookup key to choose a bucket quickly.
2. In that bucket, call equals() to find the exact matching key.

If we override equals and not hashcode, two same objects will land in different buckets, and get() will fail.
If we override hashcode and not equals, two same objects will land in same bucket, but will be counted as different.

If a hashcode is a constant, all objects will land in same bucket.

Eg. 
class Person {
    private final String id;

    Person(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

Person a = new Person("42");
Person b = new Person("42");

System.out.println(a.equals(b)); // false
System.out.println(a.hashCode() == b.hashCode()); // true

This does not violate the Java contract: unequal objects are allowed to have identical hash codes.

Map<Person, String> map = new HashMap<>();
map.put(a, "first");

System.out.println(map.get(b)); // null


If you override equals() but not hashCode(), two objects can be logically equal but have different default hash codes. This breaks lookups in HashMap and HashSet.

class Person {
    private final String id;

    Person(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person other)) return false;

        return id.equals(other.id);
    }

    // hashCode() is NOT overridden
}


Person a = new Person("42");
Person b = new Person("42");

System.out.println(a.equals(b));                 // true
System.out.println(a.hashCode() == b.hashCode()); // usually false


HashMap first uses hashCode() to choose a bucket. Since a and b usually get different default hash codes, they go to different buckets; it never gets a chance to call equals().

Map<Person, String> map = new HashMap<>();

map.put(a, "Engineer");

System.out.println(map.get(b)); // null — unexpected

* Few important caveats:

1. == compares references; equals() compares logical value.

2. Use exactly the same meaningful fields in both methods. If equality uses id and name, hash code must use id and name too.

3. Avoid mutable map keys. If a field contributing to equals()/hashCode() changes after insertion, the key can become effectively lost
map.put(person, "value");
person.setId("new-id");  // dangerous if id is used by hashCode()
map.get(person);         // may return null

4. Hash collisions are normal. Different objects can have the same hash code; HashMap then uses equals() to distinguish them.

5. A hash code is not a unique ID and should not be persisted or used for security decisions. It may differ across JVM runs or implementations.

6. For nullable fields, use Objects.equals():
Objects.equals(this.email, other.email)

7. For arrays, equals() is usually reference-based. Use Arrays.equals() or Arrays.deepEquals() instead.
Arrays.equals(this.tags, other.tags)

8. Inheritance requires care. The simplest safe approach is often making a value class final. Otherwise, subclasses adding equality fields can break symmetry.

9. Java records generate sensible equals() and hashCode() automatically:
record Person(String id, String name) {}

10. HashSet follows the same rules as HashMap: it relies on hashCode() first, then equals().

11. TreeMap and TreeSet are different: they use a Comparator or compareTo(), not hashCode(). Ideally, ordering should be consistent with equals().

A good default for ordinary value objects is: 
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Person other)) return false;

    return Objects.equals(id, other.id)
        && Objects.equals(name, other.name);
}

@Override
public int hashCode() {
    return Objects.hash(id, name);
}

For a Person, decide first what “same person” means: same database id, or same id plus name. That business decision determines both methods.

## Java Interview Sheet - Basic to Intermediate

1. What is the difference b/w stack, heap, and method area.

The stack is per thread and store method call frames, local primitive values, and object references.
The heap is shared and stores objects and arrays. 
The method area is a JVM specification concept for class-level metadata such as class structure, methods,
and runtime constants; in modern Hotspot JVMs this is mainly implemented using Metaspace rather than PermGen.

public class Demo {
    static int count = 0;

    void createUser() {
        int age = 25;
        User user = new User("Parag");
    }
}

| Memory Area | Contains in the example | Shared ? |
| ---- | ----- | ------ | 
| Stack | age, the user reference, method call data | No - one stack per thread | 
| Heap | The user ("Parag") object | Yes | 
| Method Area/Metaspace | Metadata for Demo, User, methods, runtime constants | Yes | 

**Important Details**
1. static members belong to the class, not an object instance. Use them for shared constants, utlitiy methods,
or factory methods - not mutable global state.

2. Why equal() and hashCode() be overridden together ? 
Equal objects should return same hash code. If they dont, HashMap and HashSet can behave incorrectly.

3. ArrayList versus LinkedList 
ArrayList usually default because it has fast indexed access and good memory locality. LinkedList is rarely used
in backend code.

4. HashMap versus LinkedHashMap vs TreeMap
HashMap has no guaranteed iteration order.
LinkedHashMap preserves insertion order.
TreeMap keeps keys sorted using natural order or a comparator.

5. How does HashMap work ? 
It uses the key's hashCode() to find the bucket and equals() to find the exact key within that bucket. A key must not change in a way that affects equals() or hashCode() after insertion.

6. What is the difference between Collections.unmodifiableList() and List.copyOf() ? 
unmodifiableList() creates a read-only view of the original list, so later changes to the source are still 
visible. List.copyOf() creates an immutable snapshot.

7. What happens if we insert the same key twice into a HashMap ? 
The previous value is replaced with the new value

8. Why should equals() and hashCode() be overridden together ? 
HashMap uses hashCode() to locate a bucket and then uses equals() to find the exact key inside that bucket. 
Therefore, if two objects are equal according to equals(), they must return the same hash code. Otherwise,
an equal lookup key may search a different bucket and fail to find the stored value.

map.put(new Employee(101), "Parag");
map.get(new Employee(101)); // must find "Parag"

If equals() say both Employee(101) objects are equal, but their hashcodes differ, the lookup can fail.

Also remember:
1. Equal objects -> must have the same hash code.
2. Same hash code -> objects don't have to be equal. Collisions are allowed.
3. Don't mutate fields used by equals() or hashCode() after using an object as a HashMap key or HashSet item.

9. What is the difference between `throw` and `throws`?
throw actually raises an exception. throws declares that a method can pass an exception to its caller.

10. Why use try-with-resources ? 
It automatically closes resources such as files, streams and database connections, even when an exception occurs.

11. How would you remove duplicate objects from a list ? 
List<Employee> employees = List.of(e1, e2, e1, e3);

List<Employee> uniqueEmployees = employees.stream()
        .distinct()
        .toList();

12. What is the difference between checked and unchecked exceptions ? 

Checked Exception
Compiler forces the caller to either handle or declare them.
Examples:
IOException
SQLException
FileNotFoundException
ParseException

public String readFile(Path path) throws IOException {
    return Files.readString(path);
}

Caller must handle or declare them
try {
    String content = readFile(path);
} catch (IOException ex) {
    // Retry, return an error response, or log the failure
}

Unchecked Exception
These extend RuntimeException. The compiler does not force handling.
Examples:
NullPointerException
IllegalArgumentException
IllegalStateException
IndexOutOfBoundsException
NoSuchElementException

public void updateQuantity(int quantity) {
    if (quantity <= 0) {
        throw new IllegalArgumentException("quantity must be positive");
    }
}

A useful custom unchecked domain exception
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String orderId) {
        super("Order not found: " + orderId);
    }
}

Interview-safe rule:
Use checked exceptions when the caller can reasonably recover, such as handling a file or database failure. Use unchecked exceptions for invalid input, invalid state, broken invariants, or programming mistakes.

13. What are generics and why use them ? 

Generics provide compile-time type safety, such as List<Order> instead of just List. They reduce costs and 
prevent many runtime type errors.

14. How to create an immutable class in Java ? 

Create an immutable class using these rules:

1. Make the class final so it cannot be subclassed.
2. Keep all the fields private final.
3. Set all values only in the constructor.
4. Don't provide setters.
5. Defensive copy mutable inputs such as lists, maps, arrays or Date.
6. Never expose mutable internal objects directly.

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public final class EmployeeProfile {
    private final String id;
    private final String name;
    private final List<String> skills;
    private final Instant createdAt;

    public EmployeeProfile(
            String id,
            String name,
            List<String> skills,
            Instant createdAt) {

        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.skills = List.copyOf(Objects.requireNonNull(skills));
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSkills() {
        return skills;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}

List<String> skills = new ArrayList<>(List.of("Java"));

EmployeeProfile employee =
        new EmployeeProfile("101", "Parag", skills, Instant.now());

skills.add("Spring Boot");

System.out.println(employee.getSkills()); // [Java]


List.copyOf() creates an immutable copy, so changes to the original list don't affect the object.
For an array, copy it in both constructor and getter.

public final class ApiToken {
    private final byte[] value;

    public ApiToken(byte[] value) {
        this.value = value.clone();
    }

    public byte[] getValue() {
        return value.clone();
    }
}

Interview answer:
I will make the class final, fields private and final, initialize everything in the constructor, expose
no setters, and use defensive copies for mutable fields. This prevents state from changing after object 
creation.

15. Composition Vs Inheritance. Which one to prefer ? 

Prefer composition by default because it keeps coupling low and makes behaviour easier to change. Use 
inheritance only for a stable, genuine "is-a" relationship.

16. What are Java access modifiers ? 

public is visible everywhere.
protected is visible to package and subclasses.
package-private is visible inside the package
private is visible only inside the class.

17. What really happens when we write new User() ? 
Most Java developers can write clean code. Fewer can explain what the JVM actually does the moment the code runs.
That gap is usually what separates "I know Java" from "I can debug Java applications".
Two homes for your data: Heap and Stack
Every JVM thread gets its own stack - a workspace for local variables and method calls. When a method finishes, 
its stack frame disappears, no cleanup requested.
Objects, on the other hand, live on the heap - a shared memory space every thread can see. This is where new 
actually allocates memory, and its part the Garbage Collector watches.

* Not all objects are created equal: Young Vs Old Generation
Here's the insight that changes how you think about performance: most objects die young.
A request comes in, gets turned into a DTO, validated, mapped to an entity, serialized into a response - and 
almost everything created along the way is garbage within seconds.
The JVM exploits this. New objects go into the Young Generation (specifially in an area called Eden Space). 
If they survive a collection cycle, they get promoted to survivor space, and if they keep surviving, they 
eventually graduate to the Old Generation.
This is why minor GC pauses (cleaning Young Generation) are usually fast and frequent, while major GC 
pauses (cleaning old generation) are rarer but heavier. 
The "Unreachable" Myth
A lot of developers assumes this frees memory immediately.
user=null;
It doesn't. All it does is remove the reference. The object still sits in memory, now unreachable,
waiting for GC to notice and reclaim it on its own schedule. 
Reachability - not nullness - is the actual concept the Garbage Collector cares about.
So what Garbage Collection Actually do ? 
At a high level:
Find objects nothing points to anymore. Reclaim their memory. Compact what's left so allocation stays 
fast.

### Where OutOfMemoryError Actually Comes From
OutOfMemoryError: Java heap space doesn't mean "not enough RAM". It means the JVM found live, reachable 
objects that filled the heap and couldn't be evicted. Two common causes:
1. Unbounded collections List<byte[]> data = new ArrayList<>(); 
while(true) {
  data.add(new byte[1024*1024]);
}
This list keeps a reference to everything, so nothing is ever eligible for collection.

2. Accidental caches static List<Object> cache = new ArrayList<>(). A static field that only grows and 



