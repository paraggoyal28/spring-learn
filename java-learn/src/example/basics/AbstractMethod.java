/* abstract is used when we want the subclass to override the method
in superclass for sure.
there is no object of abstract class.
cannot declare abstract constructors, abstract static methods\
Any subclass of an abstract class must either implement all of the 
abstract methods in the superclass or be itself declared abstract
*/

abstract class A {
    abstract void callme();

    void callmeout() {
        System.out.println("This is a concrete implementation.");
    }
}

class B extends A {
    void callme() {
        System.out.println("B's implementation of callme.");
    }
}

class AbstractDemo {
    public static void main(String ... args) {
        B b = new B();

        b.callme();
        b.callmeout();
    }
}

/*Output
B's implementation of callme
This is a concrete implementation
*/