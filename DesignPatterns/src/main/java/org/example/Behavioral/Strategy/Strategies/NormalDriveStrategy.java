package org.example.Behavioral.Strategy.Strategies;

public class NormalDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Normal Drive ability");
    }
}
