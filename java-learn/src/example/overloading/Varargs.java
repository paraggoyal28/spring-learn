package example.overloading;


public class Varargs {

    static void vaTest(int... v) {
        System.out.println("Number of args: " + v.length + " contents: ");
        for (int x: v) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        vaTest(10);
        vaTest(1, 2, 3);
        vaTest();
    }    
}
