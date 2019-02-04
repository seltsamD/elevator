package build.model;

import java.util.HashSet;
import java.util.Set;

public class CallPanel {
    private Set<Way> plannedWays = new HashSet<>();
    private Way currentWay;
    private boolean upDirection;

    public boolean isUpDirection() {
        return currentWay.getFromFloor() < currentWay.getToFloor();
    }

    public void setCurrentWay(Way currentWay) {
        this.currentWay = currentWay;
    }

    public void go(){
        System.out.println(String.format("Start movement from %s to %s ", currentWay.getFromFloor(), currentWay.getToFloor()));
    }


    public Way addWay(int from, int to){
        Way way = new Way(from, to);
        plannedWays.add(way);

        return way;
    }

    public void arrived(){
        System.out.println(String.format("Elevator is arrived at %s floor", currentWay.getToFloor()));
    }
}
