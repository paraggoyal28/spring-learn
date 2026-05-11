class Box {
    private double width;
    private double length;
    private double depth;

    Box(Box ob) {
        weight = ob.weight;
        height = ob.height;
        depth = ob.depth;
    }


    Box(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
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
        weight = m;
    }

    BoxWeight() {
        super();
        weight = -1;
    }

    BoxWeight(double len, double w) {
        super(len);
        weight = w;
    }
}

class DemoSuper {
    public static void main(String args[]) {
        BoxWeight mybox1 = new BoxWeight(10, 20, 15, 34.3);
        BoxWeight mybox2 = new BoxWeight(2, 3, 4, 0.0761);
        BoxWeight mybox3 = new BoxWeight(); // default
        BoxWeight mycube = new BoxWeight(3, 2);
        BoxWeight myclone = new BoxWeight(mybox1);

        double vol;

        vol = mybox1.volume();
        System.out.println("Volume of mybox1 is " + vol);
        System.out.println("Weight of mybox1 is " + mybox1.weight);
        System.out.println();

        vol = mybox2.volume();
        System.out.println("Volume of mybox2 is " + vol);
        System.out.println("Weight of mybox2 is " + mybox2.weight);

        vol = mybox3.volume();
        System.out.println("Volume of mybox3 is " + vol);
        System.out.println("Weight of mybox3 is " + mybox3.weight);

        vol = myclone.volume();
        System.out.println("Volume of myclone is " + vol);
        System.out.println("Weight of myclone is " + myclone.weight);

        vol = mycube.volume();
        System.out.println("Volume of mycube is: " + vol);
        System.out.println("Weight of mycube is: " + mycube.weight);
        System.out.println();

    }
}

/*
super() is passed an object of type BoxWeight and not Box. This still invokes the 
constructor Box(Box ob). As mentioned earlier, a superclass variable can be used to 
reference any object derived from that class. Thus, we are able to pass a BoxWeight 
object to a Box constructor. 
When a subclass calls super(), it is calling the constructor of its immediate superclass.
Thus, super() always refer to the superclass immediately above the calling class. 
*/

// Second use of super

class A {
    int i;
}

class B extends A {
    int i; // this i hides the i in A 

    B(int a, int b) {
        super.i = a; // i in A
        i = b;
    }

    void show() {
        System.out.println("i in superclass " + super.i);
        System.out.println("i in subclass " + i);
    }
}

class UseSuper {
    public static void main(String args[]) {
        B subObj = new B(1, 2);

        subObj.show();
    }
}

/*
i in superclass: 1
i in subclass: 2

Although the instance variable i in B hides the i in A, super allows access to the i
defined in the superclass. As you can see, super can also be used to call methods that are
hidden by a subclass.
*/



