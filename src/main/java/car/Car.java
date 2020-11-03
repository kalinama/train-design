package car;

import car.locomotive.LocomotiveCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Car {
    protected Car next;
    protected Car prev;

    protected static final Logger logger = LoggerFactory.getLogger(Car.class);

    public void hitch(Car car) {
        checkOnHitchingWithItself(car);
        checkOnHitchingWithLocomotive(car);

        removeExistingConnections(car);
        next = car;
        car.prev = this;

        logger.info("{} is hitched front to {}.", car.getClass(), this.getClass());
    }

    private void checkOnHitchingWithItself(Car car) {
        if (this == car) {
            logger.error("Can't hitch car with itself.");
            throw new IllegalArgumentException();
        }
    }

    private void checkOnHitchingWithLocomotive(Car car) {
        logger.warn("Locomotive car can only be hitched on one side.");
        if ((car.next != null && car instanceof LocomotiveCar)
                || (prev != null && this instanceof LocomotiveCar)) {
            logger.error("New car can't be hitched, because this locomotive has already hitched on opposite side.");
            throw new IllegalArgumentException();
        }
    }

    private void removeExistingConnections(Car car){
        if (car.prev != null) {
            logger.debug("Remove the existing back connection of hitching car.");
            car.prev.next = null;
            car.prev = null;
        }
        if (next != null) {
            logger.debug("Remove the existing front connection of the car in front of which will be hitched a new one.");
            next.prev = null;
        }
    }

    public Car getNext() {
        return next;
    }

    public Car getPrev() {
        return prev;
    }
}
