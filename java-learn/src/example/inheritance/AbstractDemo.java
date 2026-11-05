package example.inheritance;


abstract class AAbstract {
    abstract void callme();

    // concrete method
    void callmetoo() {
        System.out.println("This is a concrete method.");
    }
}

class BAbstract extends AAbstract {
    void callme() {
        System.out.println("B's implementation of callme");
    }
}

public class AbstractDemo {
    public static void main(String[] args) {
        BAbstract b = new BAbstract();

        b.callme();
        b.callmetoo();
    }
}
