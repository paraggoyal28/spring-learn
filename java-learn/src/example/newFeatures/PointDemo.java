package example.newFeatures;


record Point(double x, double y) {
    public double distanceFromOrigin() {
        return Math.hypot(x, y);
    }

    
    public static double distanceBetweenPoints(Point a, Point b) {
        double dx = a.x() - b.x();
        double dy = a.y() - b.y();
        return Math.hypot(dx, dy);
    }
}


public class PointDemo {
    public static void main(String[] args) {
        var point = new Point(3, 4);
        System.out.println("X is: ");
        System.out.println(point.x());
        System.out.println("Y is: ");
        System.out.println(point.y());

        System.out.println(point.distanceFromOrigin());

        var point2 = new Point(3, 4);
        var point3 = new Point(0, 0);
        System.out.println(point.equals(point2));
        System.out.println(point == point2);
    //    System.out.println(point2.distanceFromOrigin());
        System.out.println(Point.distanceBetweenPoints(point2, point3));
    }
}