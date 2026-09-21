package example;

class OuterClass {
    static int outer_x = 10;

    int outer_y = 20;

    private int outer_private = 13;

    // inner class
    class InnerClass {
        void display() {
            // can access static members of outer class
            System.out.println("outer_x= " + outer_x);

            // can also access non-static members of outer class
            System.out.println("outer_y= " + outer_y);

            // can also access a private member of outer class
            System.out.println("outer_private: " + outer_private);
        }
    }
}


public class InnerClassDemo {
    public static void main(String[] args) {
        OuterClass outerObject = new OuterClass();

        OuterClass.InnerClass innerObject =  outerObject.new InnerClass();

        innerObject.display();
    }
}
