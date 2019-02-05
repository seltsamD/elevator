package build.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Way way = (Way) o;
        return fromFloor.equals(way.fromFloor) &&
                toFloor.equals(way.toFloor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromFloor, toFloor);
    }
}
