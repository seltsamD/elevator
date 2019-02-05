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

    @Test
    public void goToTheTopWithTwoPeoples() throws FloorNotFoundException {
        Building building = new Building(MAX_FLOOR_COUNT);
        building.getFloor(1).goUp(3);
        building.getFloor(2).goUp(4);
        building.getElevator().go();
        Assert.assertEquals(MAX_FLOOR_COUNT, building.getElevator().getCurrentFloor());
    }

    @Test
    public void goToTheTopAndGoDown() throws FloorNotFoundException {
        Building building = new Building(MAX_FLOOR_COUNT);
        building.getFloor(1).goUp(4);
        building.getFloor(2).goUp(3);
        building.getFloor(4).goDown(2);
        building.getFloor(3).goDown(2);
        building.getElevator().go();
    }

    @Test
    public void stopTest() throws FloorNotFoundException {
        Building building = new Building(MAX_FLOOR_COUNT);
        building.getFloor(1).goUp(2);
        building.getElevator().go();
        building.getElevator().stop();
        Assert.assertTrue(building.getElevator().isStoped());
        building.getElevator().stop();
        Assert.assertFalse(building.getElevator().isStoped());
    }
}
