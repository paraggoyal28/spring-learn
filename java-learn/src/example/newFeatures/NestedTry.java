package example.newFeatures;

public class NestedTry {
    public static void main(String[] args) {
        try {
            int a = args.length;

            int b = 42 / a;

            System.out.println("a = " + a);

            try {

                if (a == 1) {
                    b = 43 / (a - a); // division by zero
                }

                if (a == 2) {
                    int c[] = {3}; 
                    c[42] = 99; // generate out-of-bounds error 
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Array out of bound exception: " + e.getMessage());
            }
        } catch (ArithmeticException arithmeticException) {
            System.out.println("Divide by 0: " + arithmeticException.getMessage());
        }
    }
}
