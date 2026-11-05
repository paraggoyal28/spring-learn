package example.inheritance;

class FigureAbc {
    double dim1;
    double dim2;

    FigureAbc(double a, double b) {
        dim1 = a;
        dim2 = b;
    }

    double area() {
        System.out.println("Area for figure is undefined.");
        return 0;
    }
}


class RectangleAbc extends FigureAbc {
    RectangleAbc(double a, double b) {
        super(a, b);
    }

    double area() {
        System.out.println("Inside Area for Rectangle");
        return dim1 * dim2;
    }
}

class TriangleAbc extends FigureAbc {
    TriangleAbc(double a, double b) {
        super(a, b);
    }

    double area() {
        System.out.println("Inside area of Triangle");
        return (dim1 * dim2)/2;
    }
}


public class FindArea {
    public static void main(String[] args) {
        FigureAbc f = new FigureAbc(10, 10);
        RectangleAbc r = new RectangleAbc(9, 5);
        TriangleAbc t = new TriangleAbc(10, 8);

        FigureAbc figref;

        figref = r;
        System.out.println("Area is: " + figref.area());

        figref = t;
        System.out.println("Area is: " + figref.area());

        figref = f;
        System.out.println("Area is: " + figref.area());
    }
}
