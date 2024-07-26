package org.example;

public class ElevatorCar {
    int id;
    ElevatorDisplay display;
    InternalButtons internalButtons;
    ElevatorState elevatorState;
    int currentFloor;
    Direction elevatorDirection;
    ElevatorDoor elevatorDoor;

    public ElevatorCar() {
        display = new ElevatorDisplay();
        internalButtons = new InternalButtons();
        elevatorState = ElevatorState.IDLE;
        currentFloor = 0;
        elevatorDirection = Direction.UP;
        elevatorDoor = new ElevatorDoor();
    }

    public void showDisplay() {
        display.showDisplay();
    }

    public void pressButton(int destination) {
        internalButtons.pressButton(destination, this);
    }

    public void setDisplay() {
        this.display.setDisplay(currentFloor, elevatorDirection, id);
    }



    boolean moveElevator(Direction dir, int destinationFloor) {
        int startFloor = currentFloor;
        this.elevatorDirection = dir;
        if(dir == Direction.UP) {
            for(int i = startFloor; i <= destinationFloor; ++i) {
                this.currentFloor = i;
                setDisplay();
                showDisplay();
                if (i == destinationFloor) {
                    return true;
                }
            }
            this.elevatorDirection = Direction.DOWN;
        }
        if(dir == Direction.DOWN) {
            for(int i = startFloor; i >= destinationFloor; --i) {
                this.currentFloor = i;
                setDisplay();
                showDisplay();
                if (i == destinationFloor) {
                    return true;
                }
            }
            this.elevatorDirection = Direction.UP;
        }
        return false;
    }
}



