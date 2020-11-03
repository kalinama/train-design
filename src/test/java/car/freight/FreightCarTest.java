package car.freight;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FreightCarTest {

    private FreightCar freightCar;
    private List<FreightCar.Freight> freights;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        freightCar = new FreightCar();
        freights = new ArrayList<>();
        Field field = FreightCar.class.getDeclaredField("freights");
        field.setAccessible(true);
        field.set(freightCar, freights);
    }

    @Test
    public void addNewFreightSuccessTest() {
        int weight = 100;
        freightCar.addFreight(weight);

        assertEquals(1, freights.size());
        assertEquals(weight, freights.get(0).getWeight());
        assertNotNull(freights.get(0).getId());
    }

    @Test
    public void addNewFreightThrowExceptionTest() {
        int weight1 = 100;
        int weight2 = 1000;

        freightCar.addFreight(weight1);

        exceptionRule.expect(IllegalArgumentException.class);
        freightCar.addFreight(weight2);
    }

    @Test
    public void getTotalWeightTest() {
        FreightCar.Freight freight1 = new FreightCar.Freight("1", 100);
        FreightCar.Freight freight2 = new FreightCar.Freight("2", 200);

        freights.add(freight1);
        freights.add(freight2);

        assertEquals(freight1.getWeight() + freight2.getWeight(), freightCar.calculateTotalWeight());
    }

}