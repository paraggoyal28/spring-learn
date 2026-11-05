package example.overloading;

class TestDemo {
    int a, b;

    TestDemo(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof TestDemo testDemoObj)) return false;
        return a == testDemoObj.a && b == testDemoObj.b;
    }
}

public class PassOb {
    public static void main(String args[]) {
        TestDemo ob1 = new TestDemo(100, 22);
        TestDemo ob2 = new TestDemo(100, 22);
        TestDemo ob3 = new TestDemo(-1, -1);

        System.out.println("ob1 == ob2: " + ob1.equals(ob2));
        System.out.println("ob1 == ob3: " + ob1.equals(ob3));
    }
}
