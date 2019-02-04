package build.model;

import java.util.HashSet;
import java.util.Set;

public class Elevator {
    private CallPanel callPanel = new CallPanel();
    private boolean stop;
    private int currentFloor;

    public Elevator(int maxFloorCount) {
        callPanel.addWay(1, maxFloorCount);
        setStop(false);
        currentFloor = 1;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void stop(){
        setStop(true);
        System.out.println(String.format("Elevator is stopped on the floor %s", currentFloor));
    }

    public void openDoor(){
        System.out.println("Door was opened");
    }

    public void closeDoor(){
        System.out.println("Door was closed");
    }
}
