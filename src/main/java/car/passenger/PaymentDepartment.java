package car.passenger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentDepartment {
    private final static int MAX_AGE_FOR_GETTING_DISCOUNT = 18;
    private final static int MIN_AGE_FOR_GETTING_DISCOUNT = 65;
    private static final Logger logger = LoggerFactory.getLogger(PaymentDepartment.class);

    public static Ticket getTicket(int age) {
        Ticket ticket;
        if (age < MAX_AGE_FOR_GETTING_DISCOUNT || age > MIN_AGE_FOR_GETTING_DISCOUNT) {
            ticket = Ticket.DISCOUNT;
        } else {
            ticket = Ticket.COMMON;
        }
        logger.debug("Passenger bought a ticket - {} with cost - {}.", ticket, ticket.getCost());
        return ticket;
    }
}
