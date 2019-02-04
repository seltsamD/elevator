package build.model;

public class FloorCallPanel {
    private CallPanel callPanel;
    private int currentFloor;

    public FloorCallPanel(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void goUp(int nextFloor){
        if(nextFloor > currentFloor) {
            callPanel.addWay(currentFloor, nextFloor);
        } else {
            System.out.println(String.format("You call elevator to up floor! Next floor %s must be greater then current %s", nextFloor, currentFloor));
        }
    }

    public void goDown(int nextFloor){
        if(nextFloor < currentFloor) {
            callPanel.addWay(currentFloor, nextFloor);
        } else {
            System.out.println(String.format("You call elevator to down floor! Next floor %s must be lower then current %s", nextFloor, currentFloor));
        }
    }
}
