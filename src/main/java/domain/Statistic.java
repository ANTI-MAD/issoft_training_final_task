package domain;

public class Statistic {
    private int numberOfHumansTransported;

    public Statistic() {
        this.numberOfHumansTransported = 0;
    }

    public int getNumberOfHumansTransported() {
        return numberOfHumansTransported;
    }

    public void incrementNumberOfHumansTransported() {
        this.numberOfHumansTransported++;
    }
}
