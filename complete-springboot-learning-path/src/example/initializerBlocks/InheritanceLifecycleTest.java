class Parent {
    // 1. Static field (Textual order)
    static String pStatic = log("1. Parent Static Field Initialized");

    // 5. Instance field (Textual order)
    String pInstance = log("5. Parent Instance Field Initialized");

    // 2. Static block
    static {
        System.out.println("2. Parent Static Block Executed");
    }

    // 6. Instance block
    {
        System.out.println("6. Parent Instance Block Executed");
    }

    public Parent() {
        System.out.println("7. Parent Constructor Executed");
        // SENIOR TRAP: Calling an overridable method from a constructor!
        showInfo(); 
    }

    public void showInfo() {
        System.out.println("-> Parent showInfo() called");
    }

    static String log(String msg) {
        System.out.println(msg);
        return "";
    }
}

class Child extends Parent {
    // 3. Static field
    static String cStatic = log("3. Child Static Field Initialized");

    // 8. Instance field
    String cInstance = log("8. Child Instance Field Initialized");

    // 4. Static block
    static {
        System.out.println("4. Child Static Block Executed");
    }

    // 9. Instance block
    {
        System.out.println("9. Child Instance Block Executed");
    }

    public Child() {
        // super() is implicitly invoked here first
        System.out.println("10. Child Constructor Executed");
    }

    @Override
    public void showInfo() {
        // Notice: cInstance will be null here because Child's instance fields haven't initialized yet!
        System.out.println("-> Child showInfo() called | cInstance value: " + cInstance);
    }
}

public class InheritanceLifecycleTest {
    public static void main(String[] args) {
        System.out.println("--- MAIN START ---");
        Child obj = new Child();
    }
}