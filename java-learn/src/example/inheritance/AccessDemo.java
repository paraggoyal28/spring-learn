package example.inheritance;


// Create a superclass
class A {
    int i;
    private int j;

    void setij(int x, int y) {
        i = x;
        j = y;
    }
}

class B extends A {
    int total;

    void sum() {
       // total = i + j; j  not accessible here
       total = i + 20;
    }
}


public class AccessDemo {
    public static void main(String[] args) {
        B subObj = new B();
        subObj.setij(10, 20);
        subObj.sum();
        System.out.println("Total is: " + subObj.total);
    }
}
