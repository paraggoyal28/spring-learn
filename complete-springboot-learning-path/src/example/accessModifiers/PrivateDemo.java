package example.accessModifiers;

class Student {
    private int age;

    Student() {
        this.age = 20;
    }

    public int getAge() {
        return age;
    }
}

public class PrivateDemo {
    public static void main(String args[]) {
        Student s = new Student();
        System.out.println("Private access: age is hidden inside Student and exposed through a getter.");
        System.out.println("Student age: " + s.getAge());
    }
}
