package example;


class StaticOuterClass {
    static int outer_x = 10;

    // instance (non-static) member
    int outer_y = 20;

    // private member
    private static int outer_private = 30;

    static class StaticNestedClass {
        void display() {
            
            // can access static member of outer class
            System.out.println("outer_x = " + outer_x);

            // can access private static member of outer class
            System.out.println("outer_private = " + outer_private);

            // following line will give error
            // as static class cannot access instance variables 
            // of outer class
            // System.out.println("outer_y: " + outer_y);

            // Therefore, need to create an object of outer class
            StaticOuterClass obj = new StaticOuterClass();
            System.out.println("outer_y = " + obj.outer_y);
        }
    }
}

public class StaticNestedClassExample {
    public static void main(String[] args) {
        StaticOuterClass.StaticNestedClass nestedStaticObject = new StaticOuterClass.StaticNestedClass();

        nestedStaticObject.display();
    }
}
