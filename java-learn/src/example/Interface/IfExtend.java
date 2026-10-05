package example.Interface;

interface A {
    void meth1();
    void meth2();
}

// B now extends meth1() and meth2() --- it adds meth3()
interface B extends A {
    void meth3();
}

class MyClass implements A, B {
    public void meth1() {
        System.out.println("Implement meth1()");
    }

    public void meth2() {
        System.out.println("Implement meth2()");
    }
    
    public void meth3() {
        System.out.println("Implement meth3()");
    }
}

public class IfExtend {
    public static void main(String[] args) {
        MyClass ob = new MyClass();

        ob.meth1();
        ob.meth2();
        ob.meth3();
    }
}
