package domain;

import java.util.Objects;

public class Human {
    private final Double weight;
    private final Integer desiredFloorNumber;

    public Human(Double weight, Integer desiredFloorNumber) {
        this.weight = weight;
        this.desiredFloorNumber = desiredFloorNumber;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getDesiredFloorNumber() {
        return desiredFloorNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(weight, human.weight) && Objects.equals(desiredFloorNumber, human.desiredFloorNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, desiredFloorNumber);
    }
}
