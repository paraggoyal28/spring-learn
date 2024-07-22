package org.example.Behavioral.Strategy;


import org.example.Behavioral.Strategy.Strategies.SpecialDriveStrategy;

public class SportVehicle extends Vehicle {

    public SportVehicle() {
        super(new SpecialDriveStrategy());
    }
}
