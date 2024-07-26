package org.example;

public class ElevatorDisplay {
    int floor;
    Direction direction;
    int liftId;

    public void setDisplay(int floor, Direction direction, int liftId) {
        this.floor = floor;
        this.direction = direction;
        this.liftId = liftId;
    }

    public void showDisplay() {
        System.out.println(liftId);
        System.out.println(floor + "  " + direction);
    }
}
