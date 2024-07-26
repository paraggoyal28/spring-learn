package org.example;

public class InternalButtons {
    InternalDispatcher dispatcher = new InternalDispatcher();

    int[] availableButtons = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int buttonSelected;

    void pressButton(int destination, ElevatorCar elevatorCar) {

        // 1. check if destination is in the list of available floors
        buttonSelected = -1;

        for(int button: availableButtons) {
            if(button == destination) {
                buttonSelected = button;
                break;
            }
        }
        if (buttonSelected == -1) {
            return;
        }

        // 2. submit the request to the jobDispatcher
        dispatcher.submitInternalRequest(destination, elevatorCar);
    }
}
