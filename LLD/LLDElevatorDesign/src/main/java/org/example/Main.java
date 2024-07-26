package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Floor> floors = new ArrayList<>();
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        Floor floor4 = new Floor(4);
        Floor floor5 = new Floor(5);
        floors.add(floor1);
        floors.add(floor2);
        floors.add(floor3);
        floors.add(floor4);
        floors.add(floor5);

        Building building = new Building(floors);

        // lift is currently at 0 floor

        // request comes from floor 4 and direction up
        floor4.pressButton(Direction.UP);

        //  request comes from floor 5 and direction up
        floor5.pressButton(Direction.UP);

        // request comes from floor 3 and direction down
        floor3.pressButton(Direction.DOWN);

        // request comes from floor 2 and direction up
        floor2.pressButton(Direction.UP);

    }
}