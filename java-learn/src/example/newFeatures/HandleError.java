package example.newFeatures;

import java.util.Random;

public class HandleError {
    public static void main(String[] args) {
        int a = 0, b = 0, c = 0;

        Random r = new Random();

        for (int i = 0; i < 2; ++i) {
            try {
                b = r.nextInt();
                c = r.nextInt();
                System.out.println("B is " + b);
                System.out.println("C is " + c);
                a = 12345/ (b/c);
            } catch (ArithmeticException e) {
                System.out.println("Division by zero");
                a = 0; // set a to zero and continue
            }

            System.out.println("a: " + a);
        }
    }
}
