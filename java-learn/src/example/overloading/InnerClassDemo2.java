package example.overloading;


class OuterStatic {
    int outer_x = 100;
    static class Inner {
        void display() {
            OuterStatic outer = new OuterStatic();
            System.out.println("display - outer_x: " + outer.outer_x);
        }
    }
    void test() {
        for (int i = 0; i < 10; ++i) {

            Inner inner = new Inner();
            inner.display();
        }
    }
}

public class InnerClassDemo2 {
    public static void main(String[] args) {
        OuterStatic outer = new OuterStatic();
        outer.test();
    }
}
