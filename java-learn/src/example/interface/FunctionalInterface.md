# Functional Interface

1. Consumer

Accepts only one argument

Consumer<Integer> consumer = (value) -> System.out.println(value);

2. Predicate

Used for filtering in streams. Boolean valued function.

Predicate<Integer> predicate = (value) -> value != null;

3. Function

Takes one argument and returns a result. Used for transforming data

Bi-Function: Takes two arguments and return a result
Unary Operator: Input and output are of same type
Binary Operator: Like Bi-Function but with same input/output type

Function<Integer, Integer> function = (value) -> value * value;

4. Supplier

Does not take any input or argument but returns a single output.

Supplier<String> supplier = () -> "Hello, World";

* Marker Interfaces

Contains no methods or fields
Used to mark a class so that the Java runtime or compiler can identify some 
special behavior or capability of that class

public interface Serializable {}

Here, Serializable has no methods but if a class implements it, Java knows that objects 
of that class can be serialized.

1. Cloneable  - marker interface that allows an object to be cloned using Object.clone().


class A implements Cloneable
{ 
    int i;

    public A(int i) {
        this.i = i;
    }

    @Override
    protected Object clone() throws CloneNoSupportedException {
        return super.clone();
    }
}

public class Geeks {

    public static void main(String[] args) throws CloneNoSupportedException {
        A a = new A(20);

        A b = (A) a.clone();

        System.out.println(b.i);
    }
}


2. Serializable Interface

present in java.io package
Classes that don't implement serializable will not have their state serialized or deserialized.

class A implements Serializable {
    int i;
    String s;

    public A(int i, String s) {
        this.i = i;
        this.s = s;
    }
}

public class Geeks  {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        A a = new A(20, "GeeksforGeeks");

        FileOutputStream fos = new FileOutputStream("xyz.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(a);

        FileInputStream fis = new FileInputStream("xyz.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        A b = (A) ois.readObject();

        oos.close();
        ois.close();
    }
}


3. Remote Interface

Present in java.rmi

marker interface that identifies objects whose methods can be invoked remotely
from another JVM.


public interface Hello extends Remote { 
    String sayHello() throws RemoteException;
}

Java is WORA (Write Once, Run Anywhere)

JVM is different for different architectures, but the bytecode is same.

JVM Architecture

Class Loader Subsystem 

Loading: It pulls the file into memory
Linking:
- Verification: It checks if the bytecode is valid and hasn't been tampered with 
(no "hacker" code)
- Preparation: It allocates memory for static variables
- Resolution: It replaces symbolic references with direct memory references


