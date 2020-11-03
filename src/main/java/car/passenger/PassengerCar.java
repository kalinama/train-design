package car.passenger;

import car.Car;
import car.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PassengerCar extends Car {
    private final List<Passenger> passengers = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(PassengerCar.class);
    private final static int MAX_AMOUNT_OF_PASSENGER = 200;

    public static class Passenger {
        private final Person person;
        private final Ticket ticket;

        public Passenger(Person person) {
            this.person = person;
            this.ticket = PaymentDepartment.getTicket(person.getAge());
        }

        public Person getPerson() {
            return person;
        }

        public Ticket getTicket() {
            return ticket;
        }

    }

    public int calculateProceeds() {
        logger.debug("Get total proceeds of {} passengers.", passengers.size());
        return passengers.stream()
                .mapToInt(passenger -> passenger.ticket.getCost())
                .sum();
    }

    public void takePlace(Person person) {
        logger.debug("Adding new passenger with age {}.", person.getAge());
        logger.warn("Can't be more than {} passengers in a passenger car.", MAX_AMOUNT_OF_PASSENGER);

        if (passengers.size() >= MAX_AMOUNT_OF_PASSENGER) {
            logger.error("Passenger with age {} can't be added, because car has the max number of passengers.",
                    person.getAge());
            throw new RuntimeException();
        }
        Passenger passenger = new Passenger(person);
        passengers.add(passenger);
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    @Override
    public String toString() {
        return "Passenger car \n" + passengers.stream()
                .map(passenger -> "Passenger age:" + passenger.person.getAge()
                        + " Ticket: type - " + passenger.ticket.toString() + " cost - " + passenger.ticket.getCost())
                .collect(Collectors.joining("\n")) + " Total proceeds: " + calculateProceeds();
    }
}
