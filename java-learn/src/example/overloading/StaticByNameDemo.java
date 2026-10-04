package example.overloading;


class StaticDemo {
    static int a = 42;
    static int b = 99;
    static void callme() {
        System.out.println("a = " + a);
    }
}

class Parent {
    static int a = 20;

    Parent() {
        print();
    }

    static void print() {
        System.out.println("Inside static method print of parent class");
        System.out.println("a = " + a);
    }
}

class Child extends Parent {

    int b = 55;

    Child() {
        print();
    }


    static void print() {
        System.out.println("Inside child method");
    }
}


public class StaticByNameDemo {
    public static void main(String[] args) {
        StaticDemo.callme();
        System.out.println("b = " + StaticDemo.b); 
        new Child();
    }   
}
