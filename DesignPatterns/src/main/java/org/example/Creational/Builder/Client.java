package org.example.Creational.Builder;

public class Client {
    public static void main(String args[]) {
        Director director1 = new Director(new EngineeringStudentBuilder());
        Director director2 = new Director(new MBAStudentBuilder());

        Student engineerStudent = director1.createStudent();
        Student mbaStudent = director2.createStudent();

        System.out.println(engineerStudent);
        System.out.println(mbaStudent);
    }
}
