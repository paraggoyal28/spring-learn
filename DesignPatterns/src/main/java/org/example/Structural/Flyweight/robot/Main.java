package org.example.Structural.Flyweight.robot;

public class Main {
    public static void main(String[] args) {
        IRobot humanoidRobot1 = RoboticFactory.createRobot(RoboticType.HUMANOID);
        humanoidRobot1.display(1, 2);

        IRobot humanoidRobot2 = RoboticFactory.createRobot(RoboticType.HUMANOID);
        humanoidRobot2.display(10, 30);

        IRobot robotDog1 = RoboticFactory.createRobot(RoboticType.ROBOTIC_DOG);
        robotDog1.display(2, 9);

        IRobot robotDog2 = RoboticFactory.createRobot(RoboticType.ROBOTIC_DOG);
        robotDog2.display(11, 19);
    }
}
