package example.overloading;

class BoxV {
    double width;
    double height;
    double depth;

    BoxV(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }

    BoxV() {
        width = -1;
        height = -1;
        depth = -1;
    }

    BoxV(double len) {
        width = height = depth = len;
    }

    double volume() {
        return width * height * depth;
    }
}

public class BoxDemo {
    public static void main(String args[]) {
        BoxV mybox1 = new BoxV(10, 20, 30);
        BoxV mybox2 = new BoxV();
        BoxV mybox3 = new BoxV(7);

        double vol;
        vol = mybox1.volume();
        System.out.println("Volume of mybox1 is: " + vol);

        vol = mybox2.volume();
        System.out.println("Volume of mybox2 is: " + vol);

        vol = mybox3.volume();
        System.out.println("Volume of mybox3 is: " + vol);
    }
}
