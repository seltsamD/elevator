package build;

import build.exception.FloorNotFoundException;
import build.model.Building;

public class Main {
    public static void main(String[] args) throws FloorNotFoundException {
        Building building = new Building(4);
        building.getFloor(1).goUp(4);
        building.getFloor(3).goDown(2);
        building.getFloor(4).goDown(1);
        building.getElevator().go();
    }
}
