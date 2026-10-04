package example.overloading;


class Test {
    int a, b;

    Test(int a, int b) {
        this.a = a;
        this.b = b;
    }

    void math(Test ob) {
        this.a *= 2;
        this.b /= 2;
    }
}

public class CallByReferenceDemo {
    public static void main(String[] args) {
        Test ob = new Test(15, 20);

        System.out.println("ob.a and ob.b before call: " + ob.a + " " + ob.b);

        ob.math(ob);

        System.out.println("ob.a and ob.b after call: " + ob.a + " " + ob.b);
    }   
}
