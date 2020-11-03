package car.freight;

import car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreightCar extends Car {
    private static final Logger logger = LoggerFactory.getLogger(FreightCar.class);
    private static final int MAX_WEIGHT = 500;

    private final List<Freight> freights = new ArrayList<>();

    public void addFreight(int weight) {
        logger.warn("Total weight of all freights must be less than {}.", MAX_WEIGHT);

        if (calculateTotalWeight() + weight > MAX_WEIGHT) {
            logger.error("Freight with weight - {} can't be added, because total weight mustn't be greater than {}. " +
                    "Max weight that can be added is {}", weight, MAX_WEIGHT, MAX_WEIGHT - calculateTotalWeight());
            throw new IllegalArgumentException();
        }
        Freight freight = new Freight(UUID.randomUUID().toString(), weight);
        freights.add(freight);
        logger.debug("New freight with id {} and weight {} is added.", freight.id, weight);
    }

    public int calculateTotalWeight() {
        logger.debug("Get total weight of {} freights.", freights.size());
        return freights.stream()
                .mapToInt(freight -> freight.weight)
                .sum();
    }

    @Override
    public String toString() {
        return "Freight car \n" + freights.stream()
                .map(freight -> "Freight with id:" + freight.id.toString() + " weight: " + freight.weight)
                .collect(Collectors.joining("\n")) + " Total weight: " + calculateTotalWeight();
    }

    public static class Freight {
        private final String id;
        private final int weight;

        public Freight(String id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        public String getId() {
            return id;
        }

        public int getWeight() {
            return weight;
        }
    }
}
