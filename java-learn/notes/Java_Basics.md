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


