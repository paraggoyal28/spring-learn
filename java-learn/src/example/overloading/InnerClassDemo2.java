package example.overloading;


class Outer {
    int outer_x = 100;

    void test() {
        for (int i = 0; i < 10; ++i) {
            static class Inner {
                void display() {
                    Outer outer = new Outer();
                    System.out.println("display - outer_x: " + outer.outer_x);
                }
            }
            Inner inner = new Inner();
            inner.display();
        }
    }
}

public class InnerClassDemo2 {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.test();
    }
}
