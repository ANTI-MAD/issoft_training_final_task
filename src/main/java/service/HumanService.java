package service;

import domain.Human;

import java.util.Random;

public class HumanService {
    public Human generateHuman(Integer numberOfFloors, Integer currentFloor) {
        Double weight = generateHumanWeight();
        Integer desiredFloorNumber = generateHumanDesiredFloorNumber(numberOfFloors, currentFloor);

        return new Human(weight, desiredFloorNumber);
    }

    private Double generateHumanWeight() {
        double maxWeight = 120.0;
        double minWeight = 35.0;
        return Math.random() * (maxWeight - minWeight) + minWeight;
    }

    private Integer generateHumanDesiredFloorNumber(Integer numberOfFloors, Integer currentFloor) {
        Random random = new Random();
        Integer desiredFloor = random.nextInt(numberOfFloors) + 1;
        while (desiredFloor.equals(currentFloor)) {
            desiredFloor = random.nextInt(numberOfFloors) + 1;
        }
        return desiredFloor;
    }
}
