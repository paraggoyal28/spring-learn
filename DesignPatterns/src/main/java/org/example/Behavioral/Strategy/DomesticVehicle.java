package org.example.Behavioral.Strategy;


import org.example.Behavioral.Strategy.Strategies.NormalDriveStrategy;

public class DomesticVehicle extends  Vehicle {

    public DomesticVehicle() {
        super(new NormalDriveStrategy());
    }
}
