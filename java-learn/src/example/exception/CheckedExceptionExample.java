package example.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CheckedExceptionExample {

    public static void main(String args[]) {

        try {
            FileReader fileReader = new FileReader("abc.txt");
        } catch (FileNotFoundException fe) {
            System.out.println("File Not Found Exception");
        }
    }
}
