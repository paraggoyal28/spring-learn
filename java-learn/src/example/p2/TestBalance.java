package example.p2;

import example.MyPack.*;

public class TestBalance {
    public static void main(String[] args) {
        /*
            Because Balance is public, we may use Balance
            class and call its constructor
        */
        Balance test = new Balance("J.. J.. Jaspers", 99.99);

        test.show(); // we may also call show();
    }    
}
