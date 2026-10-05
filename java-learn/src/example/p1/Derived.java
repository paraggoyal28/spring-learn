package example.p1;

public class Derived extends Protection {
    Derived() {
        System.out.println("Derived Constructor");
        System.out.println("n = " + n);

        // class only
        //System.out.println("n_pri = " + n_pri);

        System.out.println("n_pub = " + n_pub);
        System.out.println("n_pro = " + n_pro);
    }
}
