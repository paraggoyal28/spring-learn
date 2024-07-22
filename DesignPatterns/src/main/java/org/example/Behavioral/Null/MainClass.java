package org.example.Behavioral.Null;

public class MainClass {
    public static void main(String[] args) {
        Vehicle vehicle = VehicleFactory.getVehicle(VehicleType.CAR);
        printVehicleDetails(vehicle);
        vehicle = VehicleFactory.getVehicle(VehicleType.BIKE);
        printVehicleDetails(vehicle);
    }

    private static void printVehicleDetails(Vehicle vehicle) {
        System.out.println("Seating Capacity: " + vehicle.getSeatingCapacity());
        System.out.println("Fuel Capacity: " + vehicle.getFuelCapacity());
    }
}
