/*

Dynamic Method Dispatch
Mechanism by which a call to an overridden method is resolved at run time, 
rather than at compile time. Dynamic method dispatch is important because this is how
Java implements run-time polymorphism

A superclass reference variable can refer to a subclass object.
Java uses this fact to resolve calls to overridden methods at run time.
When an overridden method is called through a superclass reference, Java 
determines which version of that method to execute based upon the type of the 
object being referred at the time of the call occurs. Thus, the determination is made at
runtime. 
It is the type of the object being referred to (not the type of the reference variable),
that determines which version of an overridden method will be executed. 

*/

class A {
    void callme() {
        System.out.println("Inside A's callme method");
    }
}

class B extends A {
    void callme() {
        System.out.println("Inside B's callme method");
    }
}

class C extends A {
    void callme() {
        System.out.println("Inside C's callme method");
    }
}

class Dispatch {
    public static void main(String ... args) {
        A a = new A();
        B b = new B();
        C c = new C();
        A r;
        r = a; // r refers to an A object
        r.callme(); 

        r = b; // r refers to an B object
        r.callme();

        r = c;
        r.callme(); 
    }
}

// Output
// Inside A's callme method
// Inside B's callme method
// Inside C's callme method

