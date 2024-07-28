package org.example.garbageCollection;

class Employee {
    private final int ID;
    private final String name;
    private final int age;
    private static int nextId = 1;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
        this.ID = nextId++;
    }

    public void show() {
        System.out.println("Id = " + ID + "\nName = " + name + "\nAge = " + age);
    }

    public void showNextId() {
        System.out.println("Next employee id will be = " + nextId);
    }
}
public class GarbageCollectionDemo {
    public static void main(String[] args) {
        Employee1 gfg1 = new Employee1("GFG1", 56);
        Employee1 gfg2 = new Employee1("GFG2", 45);
        Employee1 gfg3 = new Employee1("GFG3", 25);

        gfg1.show();
        gfg2.show();
        gfg3.show();

        gfg1.showNextId();
        gfg2.showNextId();
        gfg3.showNextId();

        {
            Employee1 intern1 = new Employee1("GFG4", 23);
            Employee1 intern2 = new Employee1("GFG5", 21);
            intern1.show();
            intern2.show();
            intern1.showNextId();
            intern2.showNextId();
        }
        // will return 6 but should return 4 as interns are removed.
        gfg1.showNextId();
    }
}

