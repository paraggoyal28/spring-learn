package org.example;

public class FixedExternalDispatcher implements ExternalDispatcher {
    private static FixedExternalDispatcher instance = null;


    private FixedExternalDispatcher() {

    }

    public static FixedExternalDispatcher getInstance() {
        if(instance == null) {
            synchronized (FixedExternalDispatcher.class) {
                if (instance == null) {
                    instance = new FixedExternalDispatcher();
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
            if (elevatorId%2 == 1 && floor < 3) {
                elevatorController.submitExternalRequest(floor, direction);
            } else if (elevatorId%2 == 0 && floor >= 3){
                elevatorController.submitExternalRequest(floor, direction);
            }
        }
    }
}
