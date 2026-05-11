
class A {
    int i;
    private int j;

    void setij(int x, int y) {
        i = x;
        j = y;
    }
}

class B extends A {
    int total;
    void sum() {
        total = i + j; // Error: j is not accessible here
    }
}

public class Access {
    public static void main(String[] args) {
        B subOb = new B();
        subOb.setij(10, 12);

        subOb.sum();
        System.out.println("Total is " + subOb.total);
    }
}

/*
 Remember: A class member that has been declared as private will remain private to its class.
It is not accessible by any code outside its class, including subclasses
*/

/* 
Important to note that it is the type of the reference variable - not the type of the object
that it refers to - that determines what members can be accessed. That is, when a reference
to a subclass object is assigned to a superclass reference variable, we will have access only 
to those parts of the object defined by the superclass. This is why plainbox can't access 
weight even when it refers to a BoxWeight object
*/

/*
super is used to initialize the superclass members
This way superclass data members can be kept as private
super() one form calls the superclass constructor
super() another form calls the member of superclass hidden

