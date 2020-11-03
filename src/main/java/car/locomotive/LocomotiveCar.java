package car.locomotive;

import car.Car;
import car.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LocomotiveCar extends Car {
    private static final Logger logger = LoggerFactory.getLogger(LocomotiveCar.class);
    private final static int MAX_AMOUNT_OF_MACHINISTS = 2;
    private final static int MIN_AGE_FOR_MACHINISTS = 18;

    private final List<Person> machinists = new ArrayList<>();

    public void addMachinist(Person person) {
        logger.warn("Age of machinist can't be less than {}.", MIN_AGE_FOR_MACHINISTS);
        logger.warn("Can't be more than {} machinists in a locomotive car.", MAX_AMOUNT_OF_MACHINISTS);

        if (person.getAge() < MIN_AGE_FOR_MACHINISTS) {
            logger.error("Machinist with age {} can't be added, because age must be {} or greater.",
                    person.getAge(), MIN_AGE_FOR_MACHINISTS);
            throw new IllegalArgumentException();
        }
        if (machinists.size() >= MAX_AMOUNT_OF_MACHINISTS) {
            logger.error("Machinist with age {} can't be added, because car has the max number of machinists.",
                    person.getAge());
            throw new RuntimeException();
        }
        machinists.add(person);
    }

    public List<Person> getMachinists() {
        return Collections.unmodifiableList(machinists);
    }

    @Override
    public String toString() {
        return "Locomotive car \n" + machinists.stream()
                .map(machinist -> "Machinist age:" + machinist.getAge())
                .collect(Collectors.joining("\n"));
    }
}
