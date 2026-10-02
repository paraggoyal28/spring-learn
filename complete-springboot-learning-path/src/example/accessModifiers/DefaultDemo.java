package example.accessModifiers;

class StudentDefault {
    int age;

    StudentDefault() {
        this.age = 20;
    }
}

public class DefaultDemo {
    public static void main(String args[]) {
        StudentDefault s = new StudentDefault();
        System.out.println("Default access: the class and field are accessible within this package.");
        System.out.println("Student age: " + s.age);
    }
}
