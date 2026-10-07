package example.newFeatures;
import java.util.*;

record Point(double x, double y) {
    public Point() { 
        this(0, 0); 
    }

    public double distanceFromOrigin() {
        return Math.hypot(x, y);
    }

    // A static field and method
    public static Point ORIGIN = new Point();

    public static double distance(Point p, Point q) {
        return Math.hypot(p.x - q.x, p.y - q.y);
    }
}

record PointInTime(double x, double y, Date when) {}

record Range(int from, int to) {
    // A compact constructor
    public Range {
        if (from > to) { // Swap the bounds
            int temp = from;
            from = to;
            to = temp;
        }
    }
}

public class RecordTest {
    public static void main(String[] args) {
        var p = new Point(3, 4);
        System.out.println("Coordinates of p: " + p.x() + " " + p.y());
        System.out.println("Distance From origin: " + p.distanceFromOrigin());

        // A mutable record
        var pt = new PointInTime(3, 4, new Date());
        System.out.println("Before: " + pt);

        pt.when().setTime(0);

        System.out.println("After: " + pt);

        // Invoking a compact constructor

        var r = new Range(4, 8);
        System.out.println("r: " + r);
    }
}
