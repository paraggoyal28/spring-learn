package org.example;

public class EvenOddExternalDispatcher implements ExternalDispatcher {
    private static EvenOddExternalDispatcher instance = null;
    private EvenOddExternalDispatcher() {

    }

    public static EvenOddExternalDispatcher getInstance() {
        if(instance == null) {
            synchronized (EvenOddExternalDispatcher.class) {
                if (instance == null) {
                    instance = new EvenOddExternalDispatcher();
                }
                return instance;
            }
        }
        return instance;
    }

    @Override
    public void submitExternalRequest(int floor, Direction direction) {
        for(ElevatorController elevatorController : elevatorControllerList) {
            int elevatorId = elevatorController.elevatorCar.id;
            if (elevatorId%2 == 1 && floor%2 == 1) {
                elevatorController.submitExternalRequest(floor, direction);
            } else if (elevatorId%2 == 0 && floor%2 == 0){
                elevatorController.submitExternalRequest(floor, direction);
            }
        }
    }
}
