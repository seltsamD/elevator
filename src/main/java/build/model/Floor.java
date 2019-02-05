package build.model;

public class Floor {
    private int floorNumber;
    private FloorCallPanel floorCallPanel;

    public Floor(int floorNumber, ControlPanel controlPanel) {
        this.floorNumber = floorNumber;
        this.floorCallPanel = new FloorCallPanel(floorNumber, controlPanel);
    }

    public void goUp(Integer nextFloor) {
        floorCallPanel.goUp(nextFloor);
    }

    public void goDown(Integer nextFloor) {
        floorCallPanel.goDown(nextFloor);
    }

}
