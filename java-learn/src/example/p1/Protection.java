package example.p1;

public class Protection {
    int n = 1; // default
    private int n_pri = 2;
    protected int n_pro = 3;
    public int n_pub = 4;

    public Protection() {
        System.out.println("Base Constructor");
        System.out.println("n = " + n);
        System.out.println("n_pri = " + n_pri);
        System.out.println("n_pub = " + n_pub);
        System.out.println("n_pro = " + n_pro);
    }
}
