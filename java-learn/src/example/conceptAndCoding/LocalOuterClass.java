public class LocalOuterClass {
    int instanceVariable = 10;
    static int classVariable = 20;

    public void display() {

        int methodLocalVariable = 40;

        class LocalInnerClass {
            int localInstanceVariable = 50;

            public void print() {
                System.out.println("Sum is " + (instanceVariable + classVariable + methodLocalVariable + localInstanceVariable));
            }
        }

        LocalInnerClass innerObj = new LocalInnerClass();
        innerObj.print();
    }
}