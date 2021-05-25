package domain;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import service.ElevatorService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
public class ElevatorExecutor extends Thread{
    private final ElevatorService elevatorService = new ElevatorService();
    private final Elevator elevator;
    private final Building building;

    public ElevatorExecutor(Elevator elevator, Building building) {
        this.elevator = elevator;
        this.building = building;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            while (!elevator.getButton().equals(Button.NONE)) {
                if (elevator.getButton().equals(Button.UP)) {
                    moveUp();
                } else if (elevator.getButton().equals(Button.DOWN)) {
                    moveDown();
                }
            }
        }
    }

    public void moveUp() {
        building.getFloors().forEach(floor -> {
            log.info("Current floor {}", floor.getNumber());
            elevatorService.moveElevator(elevator.getTimeMovementElevatorToOneFloorInSeconds());
            elevator.changeCurrentFloor(floor);
            if (floor.getButtons().contains(Button.UP) || elevatorService.unloadHumansFromElevator(elevator)) {
                elevatorService.openDoor(elevator);
                elevatorService.unloadHumansFromElevator(elevator);

                while (true) {
                    synchronized (getThreadGroup()) {
                        if (!floor.getHumansUp().isEmpty()) {
                            if (elevatorService.loadHumanIntoElevator(elevator, floor.getHumansUp().get(0))) {
                                log.info("Load user");
                                floor.getHumansUp().remove(0);
                                if (floor.getHumansUp().isEmpty()) {
                                    floor.getButtons().remove(Button.UP);
                                } else if (floor.getHumansDown().isEmpty()) {
                                    floor.getButtons().remove(Button.DOWN);
                                }
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                elevatorService.closeDoor(elevator);

                if (elevator.getFloorsNumber().isEmpty()) {
                    elevator.pushButton(Button.NONE);
                }
            }
        });

        if (!elevator.getButton().equals(Button.NONE)) {
            elevator.pushButton(Button.DOWN);
        }
    }

    public void moveDown() {
        building.getFloors().stream().collect(toListReversed()).forEach(floor -> {
            log.info("Current floor {}", floor.getNumber());
            elevatorService.moveElevator(elevator.getTimeMovementElevatorToOneFloorInSeconds());
            elevator.changeCurrentFloor(floor);
            if (floor.getButtons().contains(Button.DOWN) || elevatorService.unloadHumansFromElevator(elevator)) {
                elevatorService.openDoor(elevator);
                elevatorService.unloadHumansFromElevator(elevator);

                while (true) {
                    synchronized (getThreadGroup()) {
                        if (!floor.getHumansDown().isEmpty()) {
                            if (elevatorService.loadHumanIntoElevator(elevator, floor.getHumansDown().get(0))) {
                                log.info("Load user");
                                floor.getHumansDown().remove(0);
                                if (floor.getHumansUp().isEmpty()) {
                                    floor.getButtons().remove(Button.UP);
                                } else if (floor.getHumansDown().isEmpty()) {
                                    floor.getButtons().remove(Button.DOWN);
                                }
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                elevatorService.closeDoor(elevator);

                if (elevator.getFloorsNumber().isEmpty()) {
                    elevator.pushButton(Button.NONE);
                }
            }
        });

        if (!elevator.getButton().equals(Button.NONE)) {
            elevator.pushButton(Button.UP);
        }
    }

    private void workElevator(Floor floor) {

    }

    public static <T> Collector<T, ?, List<T>> toListReversed() {
        return Collectors.collectingAndThen(Collectors.toList(), list -> {
            Collections.reverse(list);
            return list;
        });
    }
}

