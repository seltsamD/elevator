package build;

import build.exception.FloorNotFoundException;
import build.model.Building;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    private static final Integer MAX_FLOOR_COUNT = 4;

    @Test
    public void createBuildingTest() throws FloorNotFoundException {
        Building building = new Building(MAX_FLOOR_COUNT);
        Assert.assertNotNull(building.getElevator());
        for (int i = 0; i < MAX_FLOOR_COUNT; i++) {
            Assert.assertNotNull(building.getFloor(i + 1));
        }
    }

    @Test(expected = FloorNotFoundException.class)
    public void unexpectedFloorNumber() throws FloorNotFoundException {
        Building building = new Building(MAX_FLOOR_COUNT);
        Assert.assertNotNull(building.getElevator());
        building.getFloor(0);
    }

    @Test
    public void goToTheTop() throws FloorNotFoundException {
        Building building = new Building(MAX_FLOOR_COUNT);
        building.getFloor(1).goUp(MAX_FLOOR_COUNT);
        building.getElevator().go();
        Assert.assertEquals(MAX_FLOOR_COUNT, building.getElevator().getCurrentFloor());
    }
}
