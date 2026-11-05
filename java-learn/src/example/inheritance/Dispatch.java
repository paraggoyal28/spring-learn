package example.inheritance;


class AD {
    void callme() {
        System.out.println("Inside A's callme method");
    }
}

class BD extends AD {
    void callme() {
        System.out.println("Inside B's callme method");
    }
}

class CD extends BD {
    void callme() {
        System.out.println("Inside C's callme method");
    }
}

public class Dispatch {
    public static void main(String[] args) {
        AD a = new AD();
        BD b = new BD();
        CD c = new CD();

        AD r;

        r = a;
        r.callme(); 

        r = b;
        r.callme();

        r = c;
        r.callme();
    }
}
