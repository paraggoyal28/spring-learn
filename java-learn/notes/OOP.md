### OOPS

1. DIfference bw class and object
Class - Blueprint
Object - Instance of a class

Car is a class, wherease Toyoto, BMW are objects

2. Instance variable and instance methods
Instance variables -> Object's data
Instance methods -> Object's behaviour

3. Class/Static variables
A static variable belongs to the class, not to individual objects.
- Only one shared copy exists
- Shared by all objects

class Student {
    static String college = "I2IT";
}

System.out.println(Student.college);

Student s1 = new Student();
Student s2 = new Student();

s1.college = "ABC";

System.out.println(s2.college); // ABC

Both objects share the same college.

4. Static methods
A static method belongs to the class, so it can be called without creating an object
Can access static members
Cannot access instance members/variables

class Calculator {
    static int add(int a, int b) {
        return a + b;
    }
}

int result = Calculator.add(10, 20);
// 30

class Student {
    int age = 20;

    static void show() {
        // System.out.println(age); // Error
    }
}

5. Constructor
Used to initialize an object

No return type
Called when an object is created

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

Student s = new Student("Atharva");

Key Difference:
static variable - shared by all objects
static methods - belongs to the class
instance variables - separate for each object
constructor - initializes each object

6. Encapsulation

Wrapping data and methods together in a class and restricting direct access to the data.
Usually achieved through private variables and public getters/setters

class Student {
    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

Here, age cannot be accessed directly from outside the class.
Encapsulation provides data hiding and controlled access.

7. Abstraction

Hiding implementation details
Achieved using abstract classes and interfaces

abstract class Animal {
    abstract void sound();
}

class Dog extends Animal {
    void sound {
        System.out.println("Bark");
    }
}

Interview line: Abstraction focuses on what an object does, not how it does it.

8. Inheritance

A child class acquires properties and methods of a parent class

Achieved using extends

class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {

}

Dog d = new Dog();
d.eat(); 

// Eating
Dog inherits eat from Animal
Benefit: Code reusability

9. Polymorphism

One interface/name can have multiple forms.
Two types:
1. Compile-time polymorphism - Method Overloading

void add(int a, int b) {
    System.out.println(a + b);
}

void add(int a, int b, int c) {
    System.out.println(a + b + c);
}

Method overloading can be done 
1. Different number of arguments
2. Different type of arguments
Chaning the return type won't make the methods overloaded.

2. Run-time polymorphism - Method Overriding

class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}

Animal a = new Dog();
a.sound(); // Bark

10. Important Interview Question

Encapsulation -> how data is protected
Abstraction -> what is exposed/implementation hidden
Inheritance -> Reuse parent functionality
Polymorphism -> Same method/interface, different behaviour

11. Access modifiers

Access modifiers control visibility/accessibility

private - same class only
default - same package only
protected - same package + subclasses
public - everywhere

Interview point:
Encapsulation = private fields + public getters/setters

12. Inheritance

i. Single Inheritance
One child inherits from one parent

class Animal {

}

class Dog extends Animal {

}

ii. Multilevel Inheritance

class Animal {

}

class Dog extends Animal {

}

class Puppy extends Dog {

}




