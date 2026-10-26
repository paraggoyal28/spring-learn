package Banking;

class A implements Cloneable {
    int i;

    // A class constructor
    public A(int i) {
        this.i = i;
    }

    // Overriding clone() method by simply calling Object.class.clone() method
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class Geeks {


    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A(20);

        // Cloning 'a' and holding new cloned object reference in b

        A b = (A) a.clone();

        System.out.println(b.i);
    }
}
