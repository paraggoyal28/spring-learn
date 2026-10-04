package example.overloading;

class Box {
    double width;
    double height;
    double depth;

    // copy constructor
    Box(Box obj) {
        width = obj.width;
        height = obj.height;
        depth = obj.depth;
    }

    Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    Box() {
        width = -1;
        height = -1;
        depth = -1;
    }

    Box(double len) {
        width = len;
        height = len;
        depth = len;
    }

    double volume() {
        return width * height * depth;
    }
}

public class BoxCopyDemo {
    public static void main(String args[]) {
        Box mybox1 = new Box(10, 20, 15);
        Box mybox2 = new Box();
        Box mycube = new Box(7);

        Box clonedBox = new Box(mybox1);

        double vol;

        vol = mybox1.volume();
        System.out.println("Volume of mybox1 is: " + vol);

        vol = mybox2.volume();
        System.out.println("Volume of mybox2 is: " + vol);

        // get volume of cube
        vol = mycube.volume();
        System.out.println("Volume of mycube is: " + vol);

        // get volume of clone
        vol = clonedBox.volume();
        System.out.println("Volume of clonedBox is: " + vol);
    }
}
