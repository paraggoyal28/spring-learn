package org.example;

public class Floor {
    int floorNumber;
    ExternalDispatcher externalDispatcher;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        externalDispatcher = ExternalDispatcherFactory.getExternalDispatcher(DispatcherType.FIXED);
    }

    public void pressButton(Direction direction) {
        externalDispatcher.submitExternalRequest(floorNumber, direction);
    }
}
