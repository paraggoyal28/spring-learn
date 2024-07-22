package org.example.Behavioral.Strategy;

import org.example.Behavioral.Strategy.Strategies.SpecialDriveStrategy;

public class OffRoadVehicle extends Vehicle {
    public OffRoadVehicle() {
        super(new SpecialDriveStrategy());
    }
}
