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