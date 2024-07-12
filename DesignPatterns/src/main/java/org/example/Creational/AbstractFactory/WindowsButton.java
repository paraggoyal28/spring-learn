package org.example.Creational.AbstractFactory;

public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("Windows Button created");
    }
}
