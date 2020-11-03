import car.Car;
import car.Person;
import car.freight.FreightCar;
import car.locomotive.LocomotiveCar;
import car.passenger.PassengerCar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Train {

    public static void main(String[] args) {
        // example of using
        Train train = new Train();
        List<Car> cars = new ArrayList<>();
        IntStream.range(1, 10).forEach(i -> cars.add(train.generateCar()));

        for (int i = 0; i < cars.size(); i++) {
            if (i != cars.size() - 1) {
                cars.get(i).hitch(cars.get(i + 1));
            }
        }
        LocomotiveCar locomotiveCar = new LocomotiveCar();
        locomotiveCar.addMachinist(new Person(33));

        cars.get(cars.size() - 1).hitch(locomotiveCar);
        train.start(locomotiveCar);
    }

    public void start(Car first) {
        if (!(first instanceof LocomotiveCar)) {
            throw new IllegalArgumentException();
        }

        if (first.getPrev() == null) {
            while (first != null) {
                System.out.println(first.toString());
                first = first.getNext();
            }
        } else {
            while (first != null) {
                System.out.println(first.toString());
                first = first.getPrev();
            }
        }
    }

    public Car generateCar() {
        int randomNum = (int) Math.round(Math.random());
        if (randomNum == 0) {
            FreightCar freightCar = new FreightCar();
            IntStream.range(1, 5).forEach(i -> freightCar.addFreight(getRandomNumber(20, 100)));
            return freightCar;
        } else {
            PassengerCar passengerCar = new PassengerCar();
            IntStream.range(1, 10).forEach(i -> passengerCar.takePlace(new Person(getRandomNumber(1, 90))));
            return passengerCar;
        }

    }

    private int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
