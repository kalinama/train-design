package car.passenger;

import car.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class PassengerCarTest{

    private PassengerCar passengerCar;
    private List<PassengerCar.Passenger> passengers;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        passengerCar = new PassengerCar();
        passengers = new ArrayList<>();
        Field field = PassengerCar.class.getDeclaredField("passengers");
        field.setAccessible(true);
        field.set(passengerCar, passengers);
    }

    @Test
    public void addNewPassengerSuccessTest() {
        Person person = new Person(35);
        passengerCar.takePlace(person);

        assertEquals(1, passengers.size());
        assertEquals(person, passengers.get(0).getPerson());
    }

    @Test
    public void addNewPassengerOutOfCapacityThrowExceptionTest() {
        IntStream.range(0, 200).forEach(i -> {
            passengers.add(new PassengerCar.Passenger(new Person(11)));
        });

        exceptionRule.expect(RuntimeException.class);
        passengerCar.takePlace(new Person(45));
    }

    @Test
    public void calculateProceedsTest() {
        Person person1 = new Person(35);
        Person person2 = new Person(85);

        passengerCar.takePlace(person1);
        passengerCar.takePlace(person2);

        Ticket ticket1 = passengers.get(0).getTicket();
        Ticket ticket2 = passengers.get(1).getTicket();

        assertEquals(ticket1.getCost() + ticket2.getCost(), passengerCar.calculateProceeds());
    }

}