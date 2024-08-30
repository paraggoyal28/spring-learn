package com.example.cruddemo;

import com.example.cruddemo.dao.StudentDAO;
import com.example.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		// Lambda expression
		return runner -> {
			// createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			// readStudent(studentDAO);

			// queryForStudents(studentDAO);

			// queryForStudentsByLastName(studentDAO);

			// updateStudent(studentDAO);

			// deleteStudent(studentDAO);

			// deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {

		System.out.println("Deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {

		int studentId = 3;
		System.out.println("Deleting student with id: " + studentId);
		studentDAO.delete(studentId);
		System.out.println("Student with id: " + studentId + " deleted");
	}

	private void updateStudent(StudentDAO studentDAO) {

		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student theStudent = studentDAO.findById(studentId);

		// change first name to "Scooby"
		System.out.println("Updating student ...");
		theStudent.setFirstName("John");

		// update the student
		studentDAO.update(theStudent);

		// display the updated student
		System.out.println("The updated student: " + theStudent);
	}


	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Wood");

		// display list of students
		for (Student theStudent: theStudents) {
			System.out.println(theStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for(Student theStudent: theStudents) {
			System.out.println(theStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		// create a student object
		System.out.println("Creating new student object ...");
		Student student = new Student("Daffy", "Duck", "daffy@example.com");

		// save the student
		System.out.println("Saving the student ...");
		studentDAO.save(student);

		// display the id of the saved student
		int theId = student.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating 3 student objects ...");
		Student student1 = new Student("John", "Doe", "john.doe@example.com");
		Student student2 = new Student("Kevin", "Wood", "kevin.w@example.com");
		Student student3 = new Student("Mary", "Stone", "mstone@example.com");


		// save the student objects
		System.out.println("Saving the students ...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object ...");
		Student student = new Student("Paul", "Doe", "paul@example.com");

		// save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(student);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + student.getId());
	}
}





