package example.overloading;



public class Varargs2 {
    static void vaTest(String msg, int... v) {
        System.out.println(msg + v.length + " Contents: ");

        for (int x: v) {
            System.out.print(x + " ");
        }

        System.out.println();
    }
    
    public static void main(String[] args) {
        vaTest("One Varags: ", 10);
        vaTest("Three Varargs: " , 1, 2, 3);
        vaTest("No Varargs: ");
    }    
}
