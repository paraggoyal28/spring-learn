package org.example.garbageCollection;

class Employee1 {
    private int ID;
    private String name;
    private int age;
    private static int nextId = 1;

    public Employee1(String name, int age) {
        this.name = name;
        this.age = age;
        this.ID = nextId++;
    }

    public void show() {
        System.out.println("Id = " + ID + "\nName = " + name + "\nAge = " + age) ;
    }

    public void showNextId() {
        System.out.println("Next employee id will be = " + nextId);
    }


    protected void finalize() {
        --nextId;
    }
}



public class GarbageCollectionDemo2 {
    public static void main(String[] args) {
        Employee1 E = new Employee1("GFG1", 56);
        Employee1 F = new Employee1("GFG2", 54);
        Employee1 G = new Employee1("GFG3", 45);

        E.show();
        F.show();
        G.show();

        E.showNextId();
        F.showNextId();
        G.showNextId();

        {
            // for interns

            Employee1 X = new Employee1("Intern1", 21);
            Employee1 Y = new Employee1("Intern2", 23);
            X.show();
            Y.show();
            X.showNextId();
            Y.showNextId();
            X = null;
            Y = null;
            System.gc();
            System.runFinalization();
        }

        E.showNextId();
    }
}
