package build.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ControlPanel {
    private Set<Way> plannedWays = new LinkedHashSet<>();
    private Way currentWay;
    private Elevator elevator;
    private boolean moving = false;

    private int currentFloor = 1;
    private int maxFloor = 1;
    private int minFloor = 1; //all time elevator should go at the 1 floor

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
            if (!elevator.isStoped()) {
                while (plannedWays.iterator().hasNext()) {
                    currentWay = plannedWays.iterator().next();
                    elevator.setCurrentFloor(currentWay.getFromFloor());
                    System.out.println(String.format("Start movement from %s to %s ", currentWay.getFromFloor(), currentWay.getToFloor()));
                    if (isUpDirection()) {
                        goUp();
                    } else {
                        goDown();
                    }
                    System.out.println(String.format("End movement from %s to %s ", currentWay.getFromFloor(), currentWay.getToFloor()));
                }
            }
        }

    }

    private void goUp() {
        if (currentFloor < currentWay.getFromFloor()) {
            currentFloor = currentWay.getFromFloor();
        }
        if (maxFloor < currentWay.getToFloor()) {
            maxFloor = currentWay.getToFloor();
        }
        while (++currentFloor <= maxFloor) {
            System.out.println(String.format("\t Go up to floor #%d", currentFloor));
            waitSomeTime();
            elevator.setCurrentFloor(currentFloor);
            goOutFromElevator(currentFloor);
            Way goInWay = goInElevator(currentFloor);
            //if new people want to go to the higher floor
            if (goInWay.getToFloor() > maxFloor) {
                maxFloor = goInWay.getToFloor();
            }
        }
    }

    private void goDown() {
        if (currentFloor < currentWay.getFromFloor()) {
            currentFloor = currentWay.getFromFloor();
        }
        if (minFloor > currentWay.getToFloor()) {
            minFloor = currentWay.getToFloor();
        }

        while (--currentFloor >= minFloor) {
            System.out.println(String.format("\t Go down from floor #%d", currentFloor));
            waitSomeTime();
            elevator.setCurrentFloor(currentFloor);

            //some people should go out from elevator
            goOutFromElevator(currentFloor);
            Way goInWay = goInElevator(currentFloor);
            //if new people go to the lower floor elevator also should go lower
            if (goInWay.getToFloor() < minFloor) {
                minFloor = goInWay.getToFloor();
            }

        }

    }


    private void goOutFromElevator(int currentFloor) {
        List<Way> outList;
        if (isUpDirection()) {
            outList = plannedWays.stream()
                    .filter(plannedWay -> (0 == plannedWay.getToFloor().compareTo(elevator.getCurrentFloor())
                            && plannedWay.getFromFloor() <= elevator.getCurrentFloor()))
                    .collect(Collectors.toList());
        } else {
            outList = plannedWays.stream()
                    .filter(plannedWay -> (0 == plannedWay.getToFloor().compareTo(elevator.getCurrentFloor())
                            && plannedWay.getFromFloor() >= elevator.getCurrentFloor()))
                    .collect(Collectors.toList());
        }
        if (!outList.isEmpty()) {
            arrived(currentFloor);
            System.out.println(String.format("\t\t<--- %s people(s) go out from elevator", outList.size()));
            plannedWays.removeAll(outList);
        }
    }

    private Way goInElevator(int currentFloor) {
        Optional<Way> wayOptional;
        if (isUpDirection()) {
            wayOptional = plannedWays.stream()
                    .filter(plannedWay -> (0 == plannedWay.getFromFloor().compareTo(elevator.getCurrentFloor())
                            && plannedWay.getToFloor() >= elevator.getCurrentFloor()))
                    .findFirst();
        } else {
            wayOptional = plannedWays.stream()
                    .filter(plannedWay -> (0 == plannedWay.getFromFloor().compareTo(elevator.getCurrentFloor())
                            && plannedWay.getToFloor() <= elevator.getCurrentFloor()))
                    .findFirst();
        }
        if (wayOptional.isPresent()) {
            arrived(currentFloor);
            System.out.println("\t\t---> Man comes in elevator");
            return wayOptional.get();
        } else {
            return currentWay;
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
        System.out.println(String.format("\tElevator is at %s floor", currentFloor));
        elevator.openDoor();
        elevator.closeDoor();

    }
}
