package build.model;


import build.exception.FloorNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class Building {
    private final int maxFloorCount;
    private final Map<Integer, Floor> floors;
    private final Elevator elevator;

    public Building(int maxFloorCount) {
        this.maxFloorCount = maxFloorCount;
        this.elevator = new Elevator();
        this.floors = initializeFloors();
    }

    private Map<Integer, Floor> initializeFloors() {
        Map<Integer, Floor> floors = new HashMap<>();
        for (int i = 0; i < maxFloorCount; i++) {
            Floor floor = new Floor(i + 1, elevator.getControlPanel());
            floors.put(i + 1, floor);
        }
        return floors;
    }

    public Floor getFloor(int number) throws FloorNotFoundException {
        Floor floor = floors.get(number);
        if (null == floor) {
            throw new FloorNotFoundException(number);
        } else {
            return floor;
        }
    }

    public Elevator getElevator() {
        return elevator;
    }
}
