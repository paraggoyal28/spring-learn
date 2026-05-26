class A {
    int i, j;
    A (int a, int b) {
        i = a;
        j = b;
    }

    void show() {
        System.out.println("i and j are " + i + " " + j);
    }
}

class B extends A {
    int k;

    B(int a, int b, int k) {
        super(a, b);
        this.k = k;
    }

    void show() {
        super.show();
        System.out.println("k: "  + k);
    }
}

public class Override {
    public static void main(String args[]) {
        B subObj = new B(1, 2, 3);

        subObj.show();
    }
}

// Output
// k : 3

class B extends A {
    int k;
    B (int a, int b, int c) {
        super(a, b);
        this.k = c;
    }

    void show() {
        super.show();
        System.out.println("k: " + k);
    }
} 

// Output:
// i and j: 1 2
// k: 3

/* Method overriding occurs only when the names and the type signature of the two
methods are identical. If they are not, then the two methods are simply overloaded.
For example, consider the following method
*/

class A {
    int i, j;

    A (int a, int b) {
        this.i = a;
        this.j = b;
    }

    // display i and j
    void show() {
        System.out.println("i and j are: " + i + " " + j);
    }
}

// Create a subclass by extending class A 
class B extends A {
    int k;

    B(int a, int b, int c) {
        super(a, b);
        k = c;
    }

    // overload show
    void show(String msg) {
        System.out.println(msg + k);
    }
}

public class Override {
    public static void main(String ... args) {
        B subObj = new B(1, 2, 3);

        subObj.show("This is k: "); // calls the B method
        subObj.show(); // calls the A method
    }
}


