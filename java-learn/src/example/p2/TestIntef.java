package example.p2;

import example.Interface.Callback;

public class TestIntef {
    public static void main(String args[]) {
        Callback b = new Client();
        b.callback(23);
        // cannot be called b.nonIntefMeth();
        AnotherClient ac = new AnotherClient();
        b = ac;
        b.callback(98);
    }    
}
