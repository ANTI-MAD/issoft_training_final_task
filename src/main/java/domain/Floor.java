package domain;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
public class Floor {
    private final Integer number;
    private final List<Human> humansUp;
    private final List<Human> humansDown;
    private Set<Button> buttons;
    private Integer numberOfCalls;

    public Floor(Integer number) {
        this.number = number;
        this.humansUp = new CopyOnWriteArrayList<>();
        this.humansDown = new CopyOnWriteArrayList<>();
        this.buttons = new CopyOnWriteArraySet<>();
        this.buttons.add(Button.NONE);
        this.numberOfCalls = 0;
    }

    public void addHuman(Human human) {
        if (human.getDesiredFloorNumber() < number) {
            buttons.add(Button.DOWN);
            buttons.remove(Button.NONE);
            humansDown.add(human);
            log.info("Add human in humansDown");
        } else if (human.getDesiredFloorNumber() > number) {
            buttons.add(Button.UP);
            buttons.remove(Button.NONE);
            humansUp.add(human);
            log.info("Add human in humansUp");
        }
    }

    public Integer getNumber() {
        return number;
    }

    public List<Human> getHumansUp() {
        return humansUp;
    }

    public List<Human> getHumansDown() {
        return humansDown;
    }

    public Set<Button> getButtons() {
        return buttons;
    }

    public void setButton(Set<Button> buttons) {
        this.buttons = buttons;
    }

    public Integer getNumberOfCalls() {
        return numberOfCalls;
    }

    public void updateNumberOfCalls(Integer numberOfCalls) {
        this.numberOfCalls++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return Objects.equals(number, floor.number) && Objects.equals(humansUp, floor.humansUp) && Objects.equals(humansDown, floor.humansDown) && Objects.equals(buttons, floor.buttons) && Objects.equals(numberOfCalls, floor.numberOfCalls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, humansUp, humansDown, buttons, numberOfCalls);
    }
}
