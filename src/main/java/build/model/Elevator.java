package build.model;

public class Elevator {
    public static final Integer SPEED = 1;
    public static final Integer HEIGHT = 4;
    public static final Integer TIME = HEIGHT / SPEED;


    private ControlPanel controlPanel;
    private boolean stop;
    private Integer currentFloor;


    public Elevator() {
        controlPanel = new ControlPanel(this);
        setStop(false);
        currentFloor = 1;
    }

    public void go() {
        controlPanel.go();
    }

    boolean isStop() {
        return stop;
    }

    private void setStop(boolean stop) {
        this.stop = stop;
    }

    public void stop(){
        if (isStop()) {
            setStop(false);
            System.out.println(String.format("Elevator start moving on the floor %s", currentFloor));
        } else {
            setStop(true);
            System.out.println(String.format("Elevator is stopped on the floor %s", currentFloor));
        }
    }

    void openDoor() {
        System.out.println(String.format("\tDoor was opened at the floor %s", currentFloor));
    }

    void closeDoor() {
        System.out.println(String.format("\tDoor was closed", currentFloor));
    }

    ControlPanel getControlPanel() {
        return controlPanel;
    }

    public Integer getCurrentFloor() {
        return currentFloor;
    }

    void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }
}
