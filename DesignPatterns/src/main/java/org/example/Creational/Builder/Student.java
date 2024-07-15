package org.example.Creational.Builder;

import java.util.List;

public class Student {
    int rollNumber;
    int age;
    String name;
    String fatherName;
    String motherName;
    List<String> subjects;

    public Student(StudentBuilder studentBuilder) {
        this.rollNumber = studentBuilder.rollNumber;
        this.age = studentBuilder.age;
        this.name = studentBuilder.name;
        this.fatherName = studentBuilder.fatherName;
        this.motherName = studentBuilder.motherName;
        this.subjects = studentBuilder.subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNumber: " + rollNumber +
                ", age: " + age +
                ", name: '" + name + '\'' +
                ", fatherName: '" + fatherName + '\'' +
                ", motherName: '" + motherName + '\'' +
                ", subjects: " + subjects.get(0) + ", " + subjects.get(1) + ", " + subjects.get(2);
    }
}
