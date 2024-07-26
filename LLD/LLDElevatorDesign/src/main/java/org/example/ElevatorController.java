package org.example;


public class ElevatorController {

    ElevatorCar elevatorCar;

    ElevatorController(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;

    }

    public void submitExternalRequest(int floor, Direction direction) {
        elevatorCar.moveElevator(direction, floor);
    }

    public  void submitInternalRequest(int floor) {
        if(elevatorCar.currentFloor > floor) {
            elevatorCar.moveElevator(Direction.DOWN, floor);
        } else {
            elevatorCar.moveElevator(Direction.UP, floor);
        }
    }

    public  void controlElevator() {

    };

}
