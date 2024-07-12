package org.example.Creational.AbstractFactory;

public class MainApp {
    public static void main(String args[]) {
        Application app;
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")) {
            app = new Application(new WindowsFactory());
        } else if(os.contains("mac")) {
            app = new Application(new MacFactory());
        } else {
            throw new RuntimeException("Unsupported OS");
        }
        app.paint();
    }
}
