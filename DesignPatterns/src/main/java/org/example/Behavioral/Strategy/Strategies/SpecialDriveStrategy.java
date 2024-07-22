package org.example.Behavioral.Strategy.Strategies;

public class SpecialDriveStrategy implements DriveStrategy {

    @Override
    public void drive() {
        System.out.println("Special Drive ability");
    }
}
