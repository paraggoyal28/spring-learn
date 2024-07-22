package org.example.Behavioral.Strategy;

import org.example.Behavioral.Strategy.Strategies.NormalDriveStrategy;

public class NormalVehicle extends Vehicle {

    public NormalVehicle() {
        super(new NormalDriveStrategy());
    }
}
