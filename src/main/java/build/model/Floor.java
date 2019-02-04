package build.model;

class Floor {
    private int floorNumber;
    private FloorCallPanel floorCallPanel;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.floorCallPanel = new FloorCallPanel(floorNumber);
    }

}
