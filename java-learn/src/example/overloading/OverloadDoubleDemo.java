class OverloadDouble {
    void test() {
        System.out.println("No parameters");
    }

    void test(int a, int b) {
        System.out.println("a and b: " + a + " " + b);
    }

    void test(double a) {
        System.out.println("Inside test double a: " + a);
    }
}

public class OverloadDoubleDemo {
    public static void main(String[] args) {
        OverloadDouble od = new OverloadDouble();
        int i = 88;

        od.test();
        od.test(10, 30);

        od.test(i);
        od.test(123.2);
    }
}
