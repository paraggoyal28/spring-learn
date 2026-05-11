class A {
    A() {
        System.out.println("Inside A's constructor");
    }
}

class B {
    B() {
        System.out.println("Inside B's constructor");
    }
}

class C {
    C() {
        System.out.println("Inside C's constructor");
    }
}

public class CallingCons {
    public static void main(String ... args) {
        C c = new C();
    }
}

/* Output
Inside A's constructor
Inside B's constructor
Inside C's constructor
*/

