package example.inheritance;

abstract class Figure {
    double dim1;
    double dim2;

    Figure(double a, double b) {
        this.dim1 = a;
        this.dim2 = b;
    }

    abstract double area();
}

class Rectangle extends Figure {
    Rectangle(double a, double b) {
        super(a, b);
    }

    double area() {
        System.out.println("Inside area for rectangle");
        return dim1 * dim2;
    }
}

class Triangle extends Figure {
    Triangle(double a, double b) {
        super(a, b);
    }

    double area() {
        System.out.println("Inside area for triangle");
        return (dim1 * dim2)/2.0;
    }
}

class A {
    int age;
}

class B extends A {
    void print() {
        System.out.println(age);
    }
}


class AbstractAreas {
    public static void main(String args[]) {
       // Figure f = new Figure(10, 20);
        Rectangle r = new Rectangle(9, 5);
        Triangle t = new Triangle(10, 8);

        Figure figRef;

        figRef = r;
        System.out.println("Area is: " + figRef.area());

        figRef = t;
        System.out.println("Area is: " + figRef.area());

        B b  = new B();
        b.print();
    }
}
