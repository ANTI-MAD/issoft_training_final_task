package domain;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Building {
    private final Integer numberOfFloors;
    private final List<Floor> floors;
    private final List<Elevator> elevators;

    public Building(Integer numberOfFloors, List<Floor> floors, List<Elevator> elevators) {
        this.numberOfFloors = numberOfFloors;
        this.floors = floors;
        this.elevators = elevators;
    }

    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
