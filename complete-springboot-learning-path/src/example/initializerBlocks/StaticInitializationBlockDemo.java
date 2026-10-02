package example;

class StaticInitializationParent {
    static {
        System.out.println("Parent static initialization block");
    }
}

public class StaticInitializationBlockDemo extends StaticInitializationParent {
    static {
        System.out.println("Child static initialization block");
    }

    public StaticInitializationBlockDemo() {
        System.out.println("Child constructor");
    }

    public static void main(String[] args) {
        System.out.println("Main method starts");
        new StaticInitializationBlockDemo();
        new StaticInitializationBlockDemo();
    }
}

/*

Parent static initialization block
Child static initialization block
Main method starts
Child constructor
Child constructor
*/