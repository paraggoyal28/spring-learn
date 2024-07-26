package org.example;

import java.util.List;

public interface ExternalDispatcher {
    List<ElevatorController> elevatorControllerList = ElevatorCreator.elevatorControllerList;

    void submitExternalRequest(int floor, Direction direction);
}
