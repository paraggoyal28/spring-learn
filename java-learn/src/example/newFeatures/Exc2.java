package example.newFeatures;

class Exc2 {
    public static void main(String[] args) {
        int d, a;

        try {
            d = 0;
            a = 42/d;

            System.out.println("This will not be printed.");
        } catch (ArithmeticException ex) {
            System.out.println("Division by zero.");
        } finally {
            System.out.println("Finally Block executed");
        }

        System.out.println("After catch exception.");
    }
}