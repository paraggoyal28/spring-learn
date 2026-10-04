package example.overloading;

class Test {
    int a; // default access
    public int b; // public access
    private int c; // private access

    // methods to access c 
    void setc(int i) {
        c = i;
    }

    int getc() {
        return c;
    }
}

public class AccessTest {
    public static void main(String[] args) {
        Test ob = new Test();

        // These are OK. a and b may be accessed directly
        ob.a = 10;
        ob.b = 20;

        // This is not OK. 
       // ob.c = 100; // Error

        ob.setc(100);
        System.out.println("a, b and c: " + ob.a + " " + ob.b + " " + ob.getc());

    }
}
