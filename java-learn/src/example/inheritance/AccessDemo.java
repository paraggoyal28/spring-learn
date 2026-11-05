package example.inheritance;


// Create a superclass
class AA {
    int i;
    private int j;

    void setij(int x, int y) {
        i = x;
        j = y;
    }
}

class BA extends AA {
    int total;

    void sum() {
       // total = i + j; j  not accessible here
       total = i + 20;
    }
}


public class AccessDemo {
    public static void main(String[] args) {
        BA subObj = new BA();
        subObj.setij(10, 20);
        subObj.sum();
        System.out.println("Total is: " + subObj.total);
    }
}
