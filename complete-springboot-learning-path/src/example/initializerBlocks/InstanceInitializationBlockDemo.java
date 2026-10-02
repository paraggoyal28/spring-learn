package example;

public class InstanceInitializationBlockDemo {
    private int value = printFieldInitialization();

    {
        System.out.println("Instance initialization block");
    }

    public InstanceInitializationBlockDemo() {
        System.out.println("No-argument constructor body");
    }

    public InstanceInitializationBlockDemo(String name) {
        this();
        System.out.println("Parameterized constructor body: " + name);
    }

    private static int printFieldInitialization() {
        System.out.println("Instance field initializer");
        return 1;
    }

    public static void main(String[] args) {
        System.out.println("Create with no-argument constructor:");
        new InstanceInitializationBlockDemo();

        System.out.println("Create with this()-chained constructor:");
        new InstanceInitializationBlockDemo("interview example");
    }
}

/*
Create with no-argument constructor:
Instance field initializer
Instance initialization block
No-argument constructor body
Create with this()-chained constructor:
Instance field initializer
Instance initializer block
No-argument constructor body
Parameterized constructor body: Interview example

*/