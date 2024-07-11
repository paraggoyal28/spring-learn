package org.example;

import org.example.Product.Vehicle;
import org.example.Product.VehicleType;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleInventoryManagement {

    List<Vehicle> vehicles;

    public VehicleInventoryManagement(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> getVehicles(VehicleType vehicleType) {
        // filtering
        return vehicles.stream().filter(vehicle -> vehicle.getVehicleType().equals(vehicleType)).collect(Collectors.toList());
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    // crud operations on vehicles
}
