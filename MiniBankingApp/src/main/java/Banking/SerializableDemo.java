package Banking;

import java.io.*;

class AS implements Serializable {
    int i;
    String s;
    transient double salary;
    // A class constructor
    public AS(int i, String s, double salary) {
        this.i = i;
        this.s = s;
        this.salary = salary;
    }
}

public class SerializableDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AS a = new AS(20, "GeeksForGeeks", 34.34);

        // Serialize 's'
        FileOutputStream fos = new FileOutputStream("xyz.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(a);

        // De-serialize 'a'
        FileInputStream fis = new FileInputStream("xyz.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        AS b = (AS) ois.readObject(); // down-casting object

        System.out.println(b.i + " " + b.s + " " + b.salary);

        // closing streams
        oos.close();
        ois.close();
    }
}
