package car;

import car.freight.FreightCar;
import car.locomotive.LocomotiveCar;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertSame;

public class CarTest {

    private Car car1;
    private Car car2;
    private Car car3;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() {
        car1 = new FreightCar();
        car2 = new FreightCar();
        car3 = new FreightCar();
    }
    @Test
    public void hitchWithoutExistedConnection(){
        car1.hitch(car2);
        car2.hitch(car3);

        assertSame(car3.prev, car2);
        assertSame(car3.next, null);

        assertSame(car2.prev, car1);
        assertSame(car2.next, car3);

        assertSame(car1.prev, null);
        assertSame(car1.next, car2);
    }

    @Test
    public void hitchWithExistedConnectionOfCarToWhichWillBeHitchedNewOne(){
        car1.hitch(car2);
        car1.hitch(car3);

        assertSame(car3.prev, car1);
        assertSame(car1.next, car3);
        assertSame(car2.prev, null);
    }

    @Test
    public void hitchWithExistedConnectionOfHitchingCar(){
        car2.hitch(car1);
        car3.hitch(car1);

        assertSame(car1.prev, car3);
        assertSame(car1.next, null);
        assertSame(car3.next, car1);
        assertSame(car2.next, null);
    }

    @Test
    public void hitchLocomotiveCarSuccess(){
        Car locomotiveCar = new LocomotiveCar();

        car1.hitch(locomotiveCar);

        assertSame(locomotiveCar.prev, car1);
        assertSame(car1.next, locomotiveCar);
    }

    @Test
    public void hitchLocomotiveCarThrowException(){
        Car locomotiveCar = new LocomotiveCar();

        locomotiveCar.hitch(car1);

        exceptionRule.expect(IllegalArgumentException.class);
        car2.hitch(locomotiveCar);
    }

    @Test
    public void hitchOfLocomotiveCarSuccess(){
        Car locomotiveCar = new LocomotiveCar();

        locomotiveCar.hitch(car1);

        assertSame(car1.prev, locomotiveCar);
        assertSame(locomotiveCar.next, car1);
    }

    @Test
    public void hitchOfLocomotiveCarThrowException(){
        Car locomotiveCar = new LocomotiveCar();

        car1.hitch(locomotiveCar);

        exceptionRule.expect(IllegalArgumentException.class);
        locomotiveCar.hitch(car2);
    }

    @Test
    public void hitchItselfThrowException(){
        exceptionRule.expect(IllegalArgumentException.class);
        car1.hitch(car1);
    }
}