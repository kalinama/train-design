package car.locomotive;

import car.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LocomotiveCarTest{

    private LocomotiveCar locomotiveCar;
    private List<Person> machinists;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        locomotiveCar = new LocomotiveCar();
        machinists = new ArrayList<>();
        Field field = LocomotiveCar.class.getDeclaredField("machinists");
        field.setAccessible(true);
        field.set(locomotiveCar, machinists);
    }

    @Test
    public void addNewMachinistSuccessTest() {
        Person person = new Person(35);
        locomotiveCar.addMachinist(person);

        assertEquals(1, machinists.size());
        assertEquals(person, machinists.get(0));
    }

    @Test
    public void addNewMachinistYoungThrowExceptionTest() {
        Person person = new Person(12);

        exceptionRule.expect(IllegalArgumentException.class);
        locomotiveCar.addMachinist(person);
    }

    @Test
    public void addNewMachinistOutOfCapacityThrowExceptionTest() {
        Person person1 = new Person(35);
        Person person2 = new Person(39);
        Person person3 = new Person(56);

        locomotiveCar.addMachinist(person1);
        locomotiveCar.addMachinist(person2);

        exceptionRule.expect(RuntimeException.class);
        locomotiveCar.addMachinist(person3);
    }
}