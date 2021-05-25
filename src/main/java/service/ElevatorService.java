package service;

import domain.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ElevatorService {

    @SneakyThrows
    public void moveElevator(Double time) {
        Thread.sleep((long) (1000 * time));
    }

    @SneakyThrows
    public void openDoor(Elevator elevator) {
        Thread.sleep((long) (1000 * elevator.getDoorOpeningTimeInSeconds()));
    }

    @SneakyThrows
    public void closeDoor(Elevator elevator) {
        Thread.sleep((long) (1000 * elevator.getDoorClosingTimeInSeconds()));
    }

    public Boolean loadHumanIntoElevator(Elevator elevator, Human human) {
        Double weight = 0.0;

        for (int i = 0; i < elevator.getHumans().size(); i++) {
            weight += elevator.getHumans().get(i).getWeight();
        }

        if (elevator.getLiftingCapacity() > (weight + human.getWeight())) {
            log.info("Load human. Total weight = {}", weight + human.getWeight());
            elevator.addHuman(human);
            elevator.addButton(human.getDesiredFloorNumber());
            return true;
        }
        return false;
    }

    public Boolean unloadHumansFromElevator(Elevator elevator) {
        return elevator.getHumans().removeIf(human -> {
            if (human.getDesiredFloorNumber().equals(elevator.getCurrentFloor().getNumber())) {
                log.info("Unload human from an elevator");
                elevator.getStatistic().incrementNumberOfHumansTransported();
                elevator.getFloorsNumber().remove(elevator.getCurrentFloor().getNumber());

                return true;
            } else {
                return false;
            }
        });
    }
}
