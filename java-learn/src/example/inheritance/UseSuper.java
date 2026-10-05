package example.inheritance;


class A {
    int i;
}

class B extends A {
    int i; // this i hides the i in A 

    B (int a, int b) {
        super.i = a;
        i = b;
    }

    void show() {
        System.out.println("i in superclass: " + super.i);
        System.out.println("i in subclass: " + i);
    }
}

public class UseSuper {
    public static void main(String[] args) {
        B subOb = new B(12, 23);

        subOb.show();
    }
}
