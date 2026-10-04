package example.TryWithResourcesDemo;

import java.io.*;

public class PracticeEffectiveFinal {
    public static void main(String[] args) {
        StringReader reader = new StringReader("Bractice text data");

        try (reader) {
            int data = reader.read();
            System.out.println("Read first character code " + data);
        } catch (IOException io) {
            System.err.println("Read error: " + io.getMessage());   
        }

        //reader = new StringReader("Hello World");
    }
}
