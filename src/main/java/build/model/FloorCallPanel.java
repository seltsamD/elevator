package build.model;

public class FloorCallPanel {
    private ControlPanel controlPanel;
    private Integer currentFloor;

    public FloorCallPanel(Integer currentFloor, ControlPanel controlPanel) {
        this.currentFloor = currentFloor;
        this.controlPanel = controlPanel;
    }

    void goUp(Integer nextFloor) {
        if(nextFloor > currentFloor) {
            controlPanel.addWay(currentFloor, nextFloor);
        } else {
            System.out.println(String.format("You call elevator to up floor! Next floor %s must be greater then current %s", nextFloor, currentFloor));
        }
    }

    void goDown(Integer nextFloor) {
        if(nextFloor < currentFloor) {
            controlPanel.addWay(currentFloor, nextFloor);
        } else {
            System.out.println(String.format("You call elevator to down floor! Next floor %s must be lower then current %s", nextFloor, currentFloor));
        }
    }
}
