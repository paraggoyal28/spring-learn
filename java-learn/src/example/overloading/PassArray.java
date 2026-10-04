package example.overloading;


public class PassArray {
    
    static void vaTest(int v[]) {
        System.out.println("Number of arguments: " + v.length + " Contents: ");
        for (int x: v) {
            System.out.println(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n1[] = {10};
        int n2[] = {10, 20, 30};
        int n3[] = {};

        vaTest(n1);
        vaTest(n2);
        vaTest(n3);
    }
}
