package build.model;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class ControlPanel {
    private Set<Way> plannedWays = new LinkedHashSet<>();
    private Way currentWay;
    private Elevator elevator;
    private boolean moving = false;

    public ControlPanel(Elevator elevator) {
        this.elevator = elevator;
    }

    boolean isUpDirection() {
        return currentWay.getFromFloor() < currentWay.getToFloor();
    }

    public void setCurrentWay(Way currentWay) {
        this.currentWay = currentWay;
    }

    void go() {
        if (!moving) {
            if (!elevator.isStop()) {
                if (plannedWays.iterator().hasNext()) {
                    currentWay = plannedWays.iterator().next();
                    elevator.setCurrentFloor(currentWay.getFromFloor());
                    elevator.openDoor();
                    moving = true;
                    elevator.closeDoor();
                    System.out.println(String.format("Start movement from %s to %s ", currentWay.getFromFloor(), currentWay.getToFloor()));
                    plannedWays.remove(currentWay);
                    if (isUpDirection()) {
                        for (int currentFloor = currentWay.getFromFloor() + 1; currentFloor <= currentWay.getToFloor(); currentFloor++) {
                            elevator.setCurrentFloor(currentFloor);
                            System.out.println(String.format("\t Go up at next floor %s", currentFloor));
                            waitSomeTime();
                            isArrived(currentFloor);
                        }
                    } else {
                        for (int currentFloor = currentWay.getFromFloor() - 1; currentFloor >= currentWay.getToFloor(); currentFloor--) {
                            elevator.setCurrentFloor(currentFloor);
                            System.out.println(String.format("\t Go down at next floor %s", currentFloor));
                            waitSomeTime();
                            isArrived(currentFloor);
                        }
                    }

                }
            }
        }

    }

    private void isArrived(int currentFloor) {
        Optional<Way> wayOptional = plannedWays.stream()
                .filter(plannedWay -> 0 == plannedWay.getToFloor().compareTo(elevator.getCurrentFloor()))
                .findFirst();
        if (wayOptional.isPresent()) {
            arrived(currentFloor);
            plannedWays.remove(wayOptional.get());
        }
        if (currentWay.getToFloor().equals(currentFloor)) {
            arrived(currentFloor);
            go();
        }
    }

    private void waitSomeTime() {
        try {
            Thread.sleep(1000 * Elevator.TIME);
        } catch (InterruptedException e) {
            elevator.stop();
            System.out.println("Unexpected elevator stopping");
        }
    }

    Way addWay(int from, int to) {
        Way way = new Way(from, to);
        plannedWays.add(way);
        return way;
    }

    private void arrived(Integer currentFloor) {
        moving = false;
        System.out.println(String.format("Elevator is arrived at %s floor", currentFloor));
        elevator.openDoor();
        elevator.closeDoor();
    }
}
