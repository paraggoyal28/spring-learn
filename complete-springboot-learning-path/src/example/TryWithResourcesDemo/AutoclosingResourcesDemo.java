package example.TryWithResourcesDemo;

import java.io.*;

public class AutoclosingResourcesDemo {
    public static void main(String[] args) {
        File inFile = new File("D:\\spring-learn\\complete-springboot-learning-path\\src\\example\\TryWithResourcesDemo\\files\\input.txt");
        File outFile = new File("D:\\spring-learn\\complete-springboot-learning-path\\src\\example\\TryWithResourcesDemo\\files\\output.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inFile)); 
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            
            String line = reader.readLine();    
            while (line != null) {
                writer.write(line);
                writer.write("\n");
                line = reader.readLine();
            }     
            System.out.println("Data processed successfully inside the try block.");
        } catch (IOException e) {
            System.out.println("An error occurred during file operation " + e.getMessage());
        }
    }
}
