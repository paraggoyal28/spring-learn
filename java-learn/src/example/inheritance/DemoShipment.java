package example.inheritance;


class BoxW {
    private double width;
    private double height;
    private double depth;

    // Construct clone of an object
    BoxW(BoxW ob) {
        width = ob.width;
        height = ob.height;
        depth = ob.depth;
    }

    BoxW(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }

    BoxW() {
        width = -1;
        height = -1;
        depth = -1;
    }

    BoxW(double len) {
        width = height = depth = len;
    }

    double volume() {
        return width * height * depth;
    }
}

// add weight
class BoxWWeight extends BoxW {
    double weight;

    BoxWWeight(BoxWWeight ob) {
        super(ob);
        weight = ob.weight;
    }

    BoxWWeight(double w, double h, double d, double weight) {
        super(w, h, d);
        this.weight = weight;
    }

    BoxWWeight() {
        super();
        weight = -1;
    }

    BoxWWeight(double len, double m) {
        super(len);
        weight = m;
    }
}

class Shipment extends BoxWWeight {
    double cost;

    Shipment(Shipment obj) {
        super(obj);
        cost = obj.cost;
    }

    Shipment(double w, double h, double d, double m, double c) {
        super(w, h, d, m);
        cost = c;
    }

    Shipment() {
        super();
        cost = -1;
    }

    Shipment(double len, double m, double c) {
        super(len, m);
        cost = c;
    }
}


public class DemoShipment {
    public static void main(String[] args) {
        Shipment shipment1 = new Shipment(10, 20, 15, 10, 3.41);
        Shipment shipment2 = new Shipment(2, 3, 4, 0.75, 1.28);

        double vol;

        vol = shipment1.volume();
        System.out.println("Volume of shipment1 is: " + vol);
        System.out.println("Weight of shipment1 is: " + shipment1.weight);
        System.out.println("Shipping Cost is: $" + shipment1.cost);
        System.out.println();

        vol = shipment2.volume();
        System.out.println("Volume of shipment2 is: " + vol);
        System.out.println("Weight of shipment2 is: " + shipment2.weight);
        System.out.println("Shipping Cost is: $" + shipment2.cost);

    }
}
