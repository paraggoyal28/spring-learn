package org.example.Behavioral.Strategy;

public class Main {
    public static void main(String args[]) {
        Vehicle vehicle = new SportVehicle();
        vehicle.drive();
        vehicle = new NormalVehicle();
        vehicle.drive();
    }
}
