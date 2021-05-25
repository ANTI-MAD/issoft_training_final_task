package domain;

import java.util.*;

public class Elevator {
    public static final Integer DEFAULT_FLOOR_NUMBER = 1;

    private final Statistic statistic;
    private final Long id;
    private final Double liftingCapacity;
    private final Double timeMovementElevatorToOneFloorInSeconds;
    private final Double doorClosingTimeInSeconds;
    private final Double doorOpeningTimeInSeconds;
    private Floor currentFloor;
    private final List<Human> humans;
    private final Set<Integer> floorsNumber;

    private Button button;

    public Elevator(Long id, Double liftingCapacity, Double timeMovementElevatorToOneFloorInSeconds,
                    Double doorClosingTimeInSeconds, Double doorOpeningTimeInSeconds) {
        this.statistic = new Statistic();
        this.id = id;
        this.liftingCapacity = liftingCapacity;
        this.timeMovementElevatorToOneFloorInSeconds = timeMovementElevatorToOneFloorInSeconds;
        this.doorClosingTimeInSeconds = doorClosingTimeInSeconds;
        this.doorOpeningTimeInSeconds = doorOpeningTimeInSeconds;
        this.currentFloor = new Floor(DEFAULT_FLOOR_NUMBER);
        this.humans = new ArrayList<>();

        this.button = Button.NONE;
        this.floorsNumber = new TreeSet<>();
    }

    public void addButton(Integer floorNumber) {
        this.floorsNumber.add(floorNumber);
    }

    public Set<Integer> getFloorsNumber() {
        return floorsNumber;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public Long getId() {
        return id;
    }

    public Double getLiftingCapacity() {
        return liftingCapacity;
    }

    public Double getTimeMovementElevatorToOneFloorInSeconds() {
        return timeMovementElevatorToOneFloorInSeconds;
    }

    public Double getDoorClosingTimeInSeconds() {
        return doorClosingTimeInSeconds;
    }

    public Double getDoorOpeningTimeInSeconds() {
        return doorOpeningTimeInSeconds;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void changeCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public List<Human> getHumans() {
        return humans;
    }

    public void addHuman(Human human) {
        this.humans.add(human);
    }

    public Button getButton() {
        return button;
    }

    public void pushButton(Button button) {
        this.button = button;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elevator elevator = (Elevator) o;
        return Objects.equals(statistic, elevator.statistic) && Objects.equals(id, elevator.id) && Objects.equals(liftingCapacity, elevator.liftingCapacity) && Objects.equals(timeMovementElevatorToOneFloorInSeconds, elevator.timeMovementElevatorToOneFloorInSeconds) && Objects.equals(doorClosingTimeInSeconds, elevator.doorClosingTimeInSeconds) && Objects.equals(doorOpeningTimeInSeconds, elevator.doorOpeningTimeInSeconds) && Objects.equals(currentFloor, elevator.currentFloor) && Objects.equals(humans, elevator.humans) && Objects.equals(floorsNumber, elevator.floorsNumber) && button == elevator.button;
    }

    @Override
    public int hashCode() {
        return Objects.hash(statistic, id, liftingCapacity, timeMovementElevatorToOneFloorInSeconds, doorClosingTimeInSeconds, doorOpeningTimeInSeconds, currentFloor, humans, floorsNumber, button);
    }
}
