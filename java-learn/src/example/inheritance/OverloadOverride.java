package example.inheritance;

class AO {
    int i, j;

    AO(int a, int b) {
        this.i = a;
        this.j = b;
    }

    // display i and j
    void show() {
        System.out.println("i and j are: " + i + " " + j);
    }
}

class BO extends AO {
    int k;

    BO(int a, int b, int c) {
        super(a, b);
        this.k = c;
    }

    void show(String msg) {
        System.out.println(msg + k);
    }

}

public class OverloadOverride {
    public static void main(String[] args) {
        BO subOb = new BO(1, 2, 3);

        subOb.show("This is k: "); // this calls show() in B
        subOb.show(); // this calls show() in A  
    }
}
