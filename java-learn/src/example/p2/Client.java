package example.p2;

import example.Interface.*;

public class Client implements Callback {
    public void callback(int p) {
        System.out.println("Callback called with: " + p);
    }

    void nonIntefMeth() {
        System.out.println("Classes that implement interfaces may also define other members too");
    }
}
