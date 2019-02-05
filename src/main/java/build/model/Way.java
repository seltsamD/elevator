package build.model;

public class Way {
    private Integer fromFloor;
    private Integer toFloor;

    Way(Integer fromFloor, Integer toFloor) {
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
    }

    Integer getFromFloor() {
        return fromFloor;
    }

    Integer getToFloor() {
        return toFloor;
    }
}
