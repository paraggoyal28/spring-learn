package org.example.Structural.Flyweight.robot;

public class HumanoidRobot implements IRobot {
    private final RoboticType type;
    private final Sprites body; // small 2D bitmap (graphic element)

    HumanoidRobot(RoboticType type, Sprites body) {
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
        System.out.println("Robot placed at " + x + " and " + y + " and robot object is " + this);
    }
}
