package org.example.Creational.Factory;

public class MainClass {
    public static void main(String args[]) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape("RECTANGLE");
        shape.draw();
    }
}

