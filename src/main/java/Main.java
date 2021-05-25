import domain.Building;
import domain.Elevator;
import domain.ElevatorExecutor;
import domain.Floor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import service.BuildingService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        List<Floor> floors;
        List<Elevator> elevators;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество этажей: ");
        int numberOfFloors = scanner.nextInt();

        System.out.println("Введите количество лифтов: ");
        int numberOfElevators = scanner.nextInt();

        System.out.println("Введите количество людей, генерируемых в минуту: ");
        int generationIntensity = scanner.nextInt();

        floors = IntStream.rangeClosed(1, numberOfFloors).mapToObj(Floor::new).collect(Collectors.toList());

        Double liftingCapacity = 300.0;
        Double speed = 4.5;
        Double doorClosingSpeed = 2.5;
        Double doorOpeningTime = 2.5;

        elevators = IntStream.range(0, numberOfElevators).mapToObj(i -> new Elevator(Integer.toUnsignedLong(i), liftingCapacity,
                speed, doorClosingSpeed, doorOpeningTime)).collect(Collectors.toList());

        Building building = new Building(numberOfFloors, floors, elevators);
        for (Elevator elevator : elevators) {
            log.info("Create thread elevator {}", elevator.getId());
            new Thread(new ElevatorExecutor(elevator, building)).start();
        }

        final BuildingService buildingService = new BuildingService();
        buildingService.generateHumansOnRandomFloor(building, generationIntensity);
    }
}
