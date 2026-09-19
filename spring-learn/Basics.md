# Basics


1. What is classpath ? 

Classpath is like a directory map where JVM looks for classes, libraries, or resource files.
If a library like Hibernate, Jackson is included in the project build (Maven or Gradle), it gets
added to the classpath. If it is not in the classpath, JVM throws ClassNotFoundException.

2. How Spring Boot uses classpath ? 

Spring Boot uses the classpath as its primary clue to figure out what we want to build. 
This mechanism is called AutoConfiguration.

For eg. if we add spring-boot-starter-web, the spring-web and Apache Tomcat libraries get added 
to our classpath. Spring Boot sees them and automatically starts an Apache Tomcat server and 
configures a DispatcherServlet. We don't have to write any set up logic.

Another example would be if we add a database driver (like H2 or PostgreSQL) to our classpath, 
Spring Boot automatically creates a database connection pool (DataSource) bean for us.

3. What is dispatcherServlet ? 

DispatcherServlet handles all the full lifecycle of landing a request, processing it, and then returning the response.

Request Reception, to finding the right controller, invoking the method, data conversion (using Jackson), then sending the response.

Central dispatcher/controller in Spring web applications.

Acts as a single entry point for all incoming HTTP requests, routing them to right controller, and 
managing serialization and handling exceptions.

4. Problem with Traditional Spring. How Spring Boot resolves it ? 

Setting up traditional Spring requires lot of boilerplate XML configurations, manual dependency version management, and tedious setup for Tomcat embedded web servers.

How Spring Boot solves it ? 

a. Auto-Configuration: Spring Boot automatically configures our application based on the dependencies present in our classpath (like if we add H2, PostgreSQL to classpath, then it configures a DataSource). 

b. Starters: Consolidated dependency descriptors (like spring-boot-starter-web or spring-boot-starter-data-jpa) pulls in all required libraries with compatible versions 
automatically.

c. Embedded Server: Comes with an embeded web servlet container (like Tomcat, Jetty), meaning we can package our app as a standalone executable JAR and run it directly like java -jar  

5. Core Annotation Breakdown

a. @SpringBootApplication - Meta annotation containing three main annotations:

i. @SpringBootConfiguration - Marks the class as source of bean definitions.
2. @EnableAutoConfiguration - Tells Spring Boot to start adding beans based on classpath settings.
3. @ComponentScan - Scan for classes having annotation @Controller, @Service, @Repository

@Component - A generic stereotype for any Spring managed component.
@Service - Specialized for business logic layer 
@Repository - Specialized for persistance layer 
@RestController - Combines @Controller and @ResponseBody, meaning every method returns the 
data directly (serialized as JSON/XML) rather than rendering a view.

6. Bean Scopes and lifecycle: 

Bean Scope:
a. Singleton (Default): Only one instance of the bean is created per Spring IoC container. Shared across all requests.

b. Prototype: A new instance of the bean is created every time it is requested.

c. Request/Session: A new instance of bean is created for every new session/request.

7. Bean lifecycle

1. Instantiation: Spring instantiates the Bean object.
2. Populate Properties: Spring injects dependencies (@Autowired or setter/constructor injection).
3. Initialization: Custom init methods (like @PostContruct) are executed.
4. Destroy: When the controller closes, destroy methods (like @PreDestroy) are executed.

8. Global Exception Handling

Instead of try/catch block everywhere, Spring can handle exceptions globally.

@ControllerAdvice or @RestControllerAdvice: A global interceptor that catches exceptions thrown 
by any controller.

@ExceptionHandler(Exception.class): Methods annotated with this specify which exception type they catch and return a clean, structured HTTP error response (eg. a custom error DTO with a 400 or 504 status code).

5. Transactional Management (@Transactional)

What it does: Ensures that a group of database calls either all succeed or all fail together (Atomically). If a runtime exception (RuntimeException) is thrown inside a @Transactional method, 
Spring automatically rolls back the database transaction.

9. Explain global exception handling.

Global exception Handling is a centralized design pattern used in backend development to 
manage exceptions thrown anywhere across an application. Instead of scattering repititive
try-catch blocks throughout controllers and service layers, a global exception handler 
intercepts unhandled exceptions, normalizes them, and transforms them into a clean, consistent
HTTP error responses.

Why use Global Exception Handling ? 

1. Code Cleanliness
2. Consistent API response

Implement Global Exception Handling in Spring Boot

Spring Boot uses @ControllerAdvice(or @RestControllerAdvice) combined with @ExceptionHandler 
to handle exceptions globally across all controllers.

Example Implementation:
SringBoot Implementation
Step 1: Define a standard error response model

public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    public ErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public long getTimestamp() { return timestamp; }
}

Step 2: Create the global advisor class

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SpringGlobalExceptionHandler {

    // Handle custom resource not found exception -> Returns 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) [
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    ]

    // Catch all handler for any other unexpected runtime exceptions -> Return 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred: " + ex.getMessage(),
            System.currentTimeMillis() 
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

Micronaut Implementation:
Micronaut approaches global exception handling via dedicated components that implement
the generic ExceptionHandler interface rather than method-level annotations.

Example Implementation:
Step 1: Implement the ExceptionHandler interface for a specific exception.

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes={ResourceNotFoundException.class, ExceptionHandler.class})
public class MicronautResourceNotFoundException implements     ExceptionHandler<ResourceNotFoundException, HttpResponse<?>> {

    @Override
    public HttpResponse<?> handle(HttpRequest request, ResourceNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.getCode(),
            exception.getMessage(),
            System.currentTimeMillis()
        );

        return HttpResponse.status(HttpStatus.NOT_FOUND).body(error);
    }
}


Step 2: Implement a catch all global fallback handler for standard exceptions

@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class MicronautGlobalExceptionHandler implements ExceptionHandler<Exception, HttpResponse<?>> {
    @Override
    public HttpResponse<?> handle(HttpRequest request, Exception exception) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.getCode(),
            "An unexpected internal server error occurred.", 
            System.currentTimeMillis()
        );

        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error); 
    }
}

# Key Framework Comparison

1. Spring Boot groups multiple exception handling methods inside a single class annotated 
with @RestControllerAdvice, mapping them via @ExceptionHander(SpecificException.class).
2. Micronaut utilizes individual, decoupled components where each exception type has its own 
dedicated handler class implementing the generic ExceptionHandler<T, R> contract where
T is type of exception and R is the return type.

# Spring Core
Spring is a Framework, unlike Java (programming language)

## Framework
* A framework is a semi-developed software that provides common logic required for project
development
* It helps developers to implement more functionality in less time.
* The code which is used repeatedly is called Boilerplate code.
* When we use a framework, we can focus only on business logic, and the framework handles common 
tasks.
 - For example, we have a directory structure in that one folder we store java files, in 
 another we store resources.
 
## Types of Frameworks
1. Frontend Framework - Angular
2. Web Framework - Strut (Outdated)
3. ORM Framework - Hibernate (Outdated) - Spring Data JPA 

By using Strut we can develop only web layer
By using Spring Data JPA we can develop only data access layer (Persistent Layer)

### Layers

1. Web layer (Controller)
2. Business Layer (Service)
3. Data Access Layer (Data Persistence Layer)

## Spring Framework

* Spring Framework is called as application development framework.
  By using Spring Framework we can deploy end to end application end to end
* like in servlet we need Tomcat to deploy our application, but in Spring we do not need 
such thing it will manage all by its own.
* Means Spring give us direct URL which we can directly HIT to postman which is our end to end 
backend application, no extra component needed.
* Free and Open Source
* Spring Framework is developed in modular fashion because of which it is loosely coupled (Systems 
are connected but have minimal dependencies on each other, allowing them to function independently).

Components Include:
1. Spring Core
2. Spring AOP
3. Spring ORM
4. Spring DAO
5. Spring Web Flow
6. Spring Context
7. Spring Web MVC

Spring Core is the common module which is used everywhere. Need to only learn Spring Core
to work on spring framework

Spring is a flexible framework, will not force to use all modules.

1. Spring Core
Base Module in Spring Framework.
Provide fundamental concepts like
a. IOC container
b. Depedency Injection
c. Bean Life Cycle Management
d. Bean Scope
e. Autowiring, etc.

2. Spring Context
Deal with configuration required for our Spring Application.

3. Spring AOP (Aspect Oriented Programming)
If we want to implement business logic and some secondary logic, then we face maintenance issue
of our project then we use AOP to separate business and secondary logic.

4. Spring JDBC
Used to simplify database communication logic.
In Java JDBC, we need to write boilerplate code, whereas in Spring JDBC, we can directly execute
query, remaining part Spring can take care of.

5. Spring MVC (Model, View, Controller)
Used to develop both (Web Application & Distributed Application)
    i. Web Application (Customer to business ECOM)
        Only one server
        End user uses this via UI
    2. Distributed Application (B2B/ web servers/ Restful services (IRCTC))
        Application which depends on multiple Nodes
        System where components are spread across multiple nodes (system) but work together as a single system
        like Microservices
        Have multiple servers
        Not compulsory to use via UI, Services talk to each other 
    
6. Spring ORM (Object Relation Mapping)

Spring Framework having integration with ORM frameworks.
If we make connection with Database.
JDBC will represent data in text format, whereas Hibernate ORM will represent data in Objects format.

Example using JDBC:

executeQuery(query)

while (rs.hasNext()) {
    rs.getString(name)
    rs.getInt(id)
}

Example using Hibernate

Student s=JPA.get()
// reduce manual efforts

7. Spring Security

Implement Authorization and Authentication
Spring Security with OAuth 2.0 (can login with Google, phone no)
Spring Security with JWT (JSON Web Token)

8. Spring Batch

Bulk operation
When we need some data in huge quantities, we use Spring Batch
Reading data from excel and store it into database table
Sending Monthly Statement to customer in Email
Sending Reminder to customer via Bulk SMS

9. Spring Test

Implement JUnit for automation and test
Unit testing framework

## Spring Core Classes

Spring Core is all about managing dependencies among the classes with loose coupling
Classes can be categoried into 3 types
i. POJO
ii. Java Bean
iii. Component

1. POJO (Plain Old Java Object)
Any java class which can be compiled with JDK only.

Ex - 

class Demo {
    int id;
    String name;
}

class Demo2 extends Thread {
    int id;
    String name;
}

class Demo3 implements Runnable {
    int id;
    String name;

    run() {

    }
}

## Java Beans

Any Java class which follow bean specification rules is called as Java Bean
i. Class should implement Serializable interface
ii. Class should have private data member (variables)
iii. Every private variable should have public getter and setter 
iv. Class should have a zero-param default constructor
Bean classes are used to write business logic and to store and retrieve data

## Component

The Java classes containing business logic is called as component class
eg. - Controller, Service

Tight Coupling
One class is completely dependant on another class.

ex-
public class Car {
    Engine e = new Engine();

    public void drive() {
        e.start();
        System.out.println("car moving...");
    }
}

public class Engine {
    public void start() {
        System.out.println("Engine started...");
    }
}

public class PetrolEngine {
    public void start() {
        System.out.println("Petrol Engine started...");
    }
}

- Here Car class is tightly coupled with Engine class
- We can't change Engine type
- We can't test Car without engine
- No flexibility

public interface Engine {
    public void start();
}

public class Car {
    Engine e = new DieselEngine();

    public void drive() {
        e.start();
        System.out.print("car moving...");
    }
}

* In this we use an interface, but still we have to make object hardcoded
* Flexible but still poor

## Factory Design Pattern

public class EngineFactory {
    public static Engine getEngine(String type) {
        if (type.equalsIgnoreCase("diesel")) {
            return new DieselEngine();
        } else if (type.equalsIgnoreCase("petrol")) {
            return new PetrolEngine();
        } else {
            throw new IllegalArgumentException("Invalid Engine type.");
        }
    }
}

// class Car
public class Car  {
    Engine e;

    public Car(Engine e) {
        this.e = e;
    } 

    public void drive() {
        e.start();
        System.out.println("car moving...");
    }
}

public class Test {
    public static void main(String[] args) {
        Engine e = EngineFactory.getEngine("diesel");
        Car car = new Car(e);
        car.drive();
    }
}

## Without Factory
- Car creates engine
- Tightly coupled
- Hard to test
- No Flexibility

## With Factory
- Engine is created via factory
- Loosely coupled
- Easy to test
- Flexibility

Consider we have two classes - Car and Engine
Factory gives an object of Engine to Car
That is dependency injection

# Dependency Injection

What is dependency injection ? 
Software design pattern used to implement inversion of control (IOC). It allows a class
to receive its dependencies from an external source rather than creating them itself.

The Problem Without Dependency Injection
Imagine a Car class that needs an Engine to run. If the Car creates its own engine using the 
new keyword.

public class Car {
    private Engine engine;

    public Car() {
        this.engine = new VBEngine(); // Tight coupling!
    }
}

The Issues:
1. Tight Coupling: Car is hardcoded to VBEngine
2. Hard to test

Solution with Dependency Injection
With DI, the dependency is passed (or injected) via the constructor

public class Car {
    private final Engine engine;

    // The engine is injected from the outside
    public Car(Engine engine) {
        this.engine = engine;
    }
}

Types of dependency injection
1. Constructor Injection (Recommended)
Makes dependencies mandatory and immutable, and makes unit testing simple since
we can pass mock objects directly
2. Setter Injection
3. Field Injection (Reflection using @Autowired in Spring, @Inject in Java EE/Micronaut)
Makes unit testing harder.

## Key benefits of Dependency Injection

1. Loose Coupling: Classes independent of how the dependencies are created.
2. High Testability: We can easily pass mock or stub implementations to classes during unit tests.
3. Maintainability & Flexibility: If we need to swap out a database implementation or service 
provider, we can change it in the configuration/container mapping without changing the core
business logic classes.

Why Spring Core is needed ? 
We want dependency injection but don't want to use factory design method.

## Another Project for understanding

Class Student
package org.student;

public class Student {
    private Course course;

    public void study() {
        int start = course.enroll();
        if (start >= 1) {
            System.out.println("Journey started...");
        } else {
            System.out.println("Payment failed...");
        }
    }
}

Interface Course
package org.student;

public interface Course {
    public int enroll();
}

Class DSA_Course
package org.student;

public class DSA_Course implements Course {
    @Override
    public int enroll() {
        return 1;
    }
}

Class Java_Fullstack
package org.student;

public class Java_Fullstack implements Course {
    @Override
    public int enroll() {
        return 1;
    }
}

package org.student;
public class App {
    public static void main(String[] args) {
        Student s = new Student();
        s.study();
    }
}

// The above program throws NullPointerException
// To solve above problem we use Dependency Injection

## Dependency Injection

1. Setter Injection

package org.example;

public class Student {
    private Course course;

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student(Course course) {
        this.course = course;
    }

    public Student() {
    }

    public void study() {
        int start = course.enroll();

    }
}

public class App {
    public static void main(String[] args)  {
        Student s = new Student(new DSA_Course());

        s.study();
    }
}

For field injection, the field should be public

public class Student {
    public Course course;

    public Student() {

    }

    public Student(Course course) {
        this.course = course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

public class App {
    public static void main(String[] args) {
        Student s = new Student();
        s.course = new DSA_Course();

    }
}

1. In manual injection, we need to make variable public otherwise it will not be available 
outside the class.
2. If we not make it public, we have to use Setter and Constructor injection.
3. But in Spring Core we no need to make a field public, it will handle the injection automatically internally.
4. Spring Core uses Java Reflection API to inject dependency, which allows it to access and 
modify private fields and methods, even though they are marked private.

field.setAccessible(true)

tells the JVM to ignore Java access modifiers (private/protected) and allow access.

## Reflection API
Allows a program to inspect and modify classes, methods, fields and constructors at runtime,
even though they are marked private.

Provided by java.lang.reflect

Why the Reflection API is used:
Frameworks like Spring uses Reflection to:
1. Create objects automatically
2. Inject dependencies
3. Access private fields and methods
4. Manage bean lifecycle

Reflection bypasses access modifiers using:
setAccessible(true)

## IOC Container (Inversion Of Control)
- Responsible for dependency injection in Spring Application
- Dependency injection means creating and injecting dependent bean object into target bean class.
The class which is compiled by java is also called as Java Bean class and object of that is 
called bean object.













