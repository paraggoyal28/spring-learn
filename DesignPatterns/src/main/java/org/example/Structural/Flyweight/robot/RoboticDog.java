package org.example.Structural.Flyweight.robot;

public class RoboticDog implements IRobot {
    private final RoboticType type;
    private final Sprites body;

    RoboticDog(RoboticType type, Sprites body) {
        this.type = type;
        this.body = body;
    }

    public RoboticType getType() {
        return type;
    }

    public Sprites getBody() {
        return body;
    }


    @Override
    public void display(int x, int y) {
        System.out.println("Robotic Dog at " + x + " and " + y + " and object is " + this);
    }
}
