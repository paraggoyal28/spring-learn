package org.example.Behavioral.Null;

public class VehicleFactory {
    public static Vehicle getVehicle(VehicleType vehicleType) {
        if(vehicleType.equals(VehicleType.CAR)) {
            return new Car();
        }
        return new NullObject();
    }
}
