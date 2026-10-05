package example.inheritance;

class Box {
    double width;
    double height;
    double depth;

    Box(Box obj) {
        width = obj.width;
        height = obj.height;
        depth = obj.depth;
    }

    Box(double w, double h, double d) {
        this.width = w;
        this.height = h;
        this.depth = d;
    }

    Box() {
        width = -1;
        height = -1;
        depth = -1;
    }

    Box(double len) {
        width = height = depth = len;
    }

    double volume() {
        return width * height * depth;
    }
}


class BoxWeight extends Box {
    double weight;

    BoxWeight(BoxWeight ob) {
        super(ob);
        weight = ob.weight;
    }

    BoxWeight(double w, double h, double d, double m) {
        super(w, h, d);
        this.weight = m;
    }

    BoxWeight() {
        super();
        weight = -1;
    }

    BoxWeight(double len, double m) {
        super(len);
        weight = m;
    }
}

public class DemoBoxWeight {
    public static void main(String[] args) {
        BoxWeight mybox1 = new BoxWeight(10, 20, 15, 24.3);
        BoxWeight mybox2 = new BoxWeight(2, 3, 4, 0.075);
        BoxWeight mybox3 = new BoxWeight(); // default
        BoxWeight mycube = new BoxWeight(3, 2);
        BoxWeight myclone = new BoxWeight(mybox1);
        double vol;

        vol = mybox1.volume();
        System.out.println("Volume of mybox1 is: " + vol);
        System.out.println("Weight of mybox1 is: " + mybox1.weight);
        
        vol = mybox2.volume();
        System.out.println("Volume of mybox2 is: " + vol);
        System.out.println("Weight of mybox2 is: " + mybox2.weight);

        vol = mybox3.volume();
        System.out.println("Volume of mybox3 is: " + vol);
        System.out.println("Weight of mybox3 is: " + mybox3.weight);

        vol = myclone.volume();
        System.out.println("Volume of myclone is: " + vol);
        System.out.println("Weight of myclone is: " + myclone.weight);
        System.out.println();

        vol = mycube.volume();
        System.out.println("Volume of mycube is: " + vol);
        System.out.println("Weight of mycube is: " + mycube.weight);
        System.out.println();

    }
}