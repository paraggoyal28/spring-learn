package org.example.Structural.Flyweight.robot;

import java.util.HashMap;
import java.util.Map;

public class RoboticFactory {
    private static final Map<RoboticType, IRobot> robotObjectCache = new HashMap<>();

    public static IRobot createRobot(RoboticType robotType) {
        if (robotObjectCache.containsKey(robotType)) {
            return robotObjectCache.get(robotType);
        }
        else  {
            switch(robotType) {
                case HUMANOID -> {
                    Sprites humanoidSprite = new Sprites();
                    IRobot humanoidRobot = new HumanoidRobot(robotType, humanoidSprite);
                    robotObjectCache.put(robotType, humanoidRobot);
                    return humanoidRobot;
                }
                case ROBOTIC_DOG -> {
                    Sprites roboticDogSprite = new Sprites();
                    IRobot roboticDog = new RoboticDog(robotType, roboticDogSprite);
                    robotObjectCache.put(robotType, roboticDog);
                    return roboticDog;
                }
                default -> {
                    return null;
                }
            }

        }
    }
}

