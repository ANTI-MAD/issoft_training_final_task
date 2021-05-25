package service;

import domain.Building;
import domain.Button;
import domain.Human;
import lombok.SneakyThrows;

import java.util.Random;

public class BuildingService {
    private final HumanService humanService = new HumanService();

    @SneakyThrows
    public void generateHumansOnRandomFloor(Building building, int generationIntensity) {

        while (true) {
            Random random = new Random();
            Integer randomFloor = random.nextInt(building.getNumberOfFloors()) + 1;

            Human human = humanService.generateHuman(building.getNumberOfFloors(), randomFloor);
            building.getFloors().get(randomFloor - 1).addHuman(human);
            building.getElevators().forEach(elevator -> {
                if (elevator.getButton().equals(Button.NONE)) {
                    elevator.addButton(randomFloor);
                    if (elevator.getCurrentFloor().getNumber() < randomFloor) {
                        elevator.pushButton(Button.UP);
                    } else if (elevator.getCurrentFloor().getNumber() > randomFloor){
                        elevator.pushButton(Button.DOWN);
                    } else if (elevator.getCurrentFloor().getNumber() > human.getDesiredFloorNumber()) {
                        elevator.pushButton(Button.DOWN);
                    } else {
                        elevator.pushButton(Button.UP);
                    }
                }
            });
            Thread.sleep(1000 *  60 / generationIntensity);

        }
    }
}
