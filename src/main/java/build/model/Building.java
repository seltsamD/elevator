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
        this.floors = initializeFloors();
        this.elevator = new Elevator(maxFloorCount);
    }

    private Map<Integer, Floor> initializeFloors() {
        Map<Integer, Floor> floors = new HashMap<>();
        for (int i = 0; i < maxFloorCount; i++) {
            Floor floor = new Floor(i);
            floors.put(i, floor);
        }
        return floors;
    }

    public Floor getFloor(int number) throws FloorNotFoundException {
        Floor floor = floors.get(number) ;
        if(null == floor){
            throw new FloorNotFoundException(number);
        } else {
            return floor;
        }
    }

    public Elevator getElevator() {
        return elevator;
    }
}
