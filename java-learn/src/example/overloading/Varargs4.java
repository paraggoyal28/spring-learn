package example.overloading;


public class Varargs4 {
    static void vaTest(int ... v) {
        System.out.print("vaTest(int ... ): " + "Number of args: " + v.length + " Contents: ");
        for (int x: v) {
            System.out.print(x + " ");
        }
        System.out.println();
    }    

    static void vaTest(boolean ... v) {
        System.out.print("vaTest(boolean ...): " + "Number of args: " + v.length + " Contents: ");
        for (boolean x: v) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    static void vaTest(int x) {
        System.out.println("x = " + x);
    }

    public static void main(String[] args) {
        vaTest(1, 2, 3);
        vaTest(true, false, true);
        vaTest(1);
        //vaTest(); // Error Ambiguous
    }
}
