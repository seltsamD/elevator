package build.exception;

public class FloorNotFoundException extends Exception {
    public FloorNotFoundException(int floor) {
        super(String.format("Floor %s not found", floor));
    }
}
