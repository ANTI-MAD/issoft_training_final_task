package service;

import domain.Button;
import domain.Elevator;
import domain.Floor;
import domain.Human;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class ElevatorServiceTest {
    private final ElevatorService elevatorService = new ElevatorService();

    @Test
    void loadHumanIntoElevatorWithInsufficientLoadingCapacity() {
        Double weight = 100.0;
        int desiredFloorNumber = 3;
        Human human = new Human(weight, desiredFloorNumber);

        Long id = 1L;
        Double liftingCapacity = 300.0;
        Double timeMovementElevatorToOneFloorInSeconds = 2.5;
        Double doorClosingTimeInSeconds = 1.5;
        Double doorOpeningTimeInSeconds = 1.5;
        Elevator elevator = new Elevator(id, liftingCapacity, timeMovementElevatorToOneFloorInSeconds,
                doorClosingTimeInSeconds, doorOpeningTimeInSeconds);

        elevator.addHuman(new Human(120.0, 4));
        elevator.addHuman(new Human(130.0, 5));
        Floor floor = new Floor(7);
        elevator.changeCurrentFloor(floor);
        elevator.pushButton(Button.DOWN);
        boolean bool = elevatorService.loadHumanIntoElevator(elevator, human);

        assertThat(bool, is(equalTo(Boolean.FALSE)));
    }

    @Test
    void successLoadHumanIntoElevator() {
        Human human = new Human(100.0, 3);
        Elevator elevator = new Elevator(1L, 300.0, 2.4, 2.4, 2.4);

        elevator.addHuman(new Human(120.0, 4));
        //elevator.addHuman(new Human(130.0, 5));
        Floor floor = new Floor(7);
        elevator.changeCurrentFloor(floor);
        elevator.pushButton(Button.DOWN);
        boolean bool = elevatorService.loadHumanIntoElevator(elevator, human);

        elevator.getHumans().forEach(human1 -> System.out.println(human1.getWeight()));
        assertThat(bool, is(equalTo(Boolean.TRUE)));
    }

    @Test
    void unloadHumansFromElevator() {
        Elevator elevator = new Elevator(1L, 300.0, 2.4, 2.4, 2.4);
        elevator.addHuman(new Human(120.0, 4));
        elevator.addHuman(new Human(130.0, 5));
        elevator.addHuman(new Human(70.0, 4));
        Floor floor = new Floor(4);
        elevator.changeCurrentFloor(floor);
        Elevator elevator1 = new Elevator(1L, 300.0, 2.4, 2.4, 2.4);
        elevator1.addHuman(new Human(130.0, 5));
        elevator1.changeCurrentFloor(floor);

        elevatorService.unloadHumansFromElevator(elevator);
        assertThat(elevator.equals(elevator1), is(equalTo(Boolean.TRUE)));
    }
}