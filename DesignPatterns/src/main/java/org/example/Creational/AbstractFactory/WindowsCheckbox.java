package org.example.Creational.AbstractFactory;

public class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Windows checkbox created");
    }
}
