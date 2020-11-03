package car.passenger;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class PaymentDepartmentTest {

    @Test
    public void getDiscountTicketForYoungTest() {
        int age = 15;
        Ticket ticket = PaymentDepartment.getTicket(age);

        assertSame(ticket, Ticket.DISCOUNT);
    }

    @Test
    public void getDiscountTicketForOldTest() {
        int age = 66;
        Ticket ticket = PaymentDepartment.getTicket(age);

        assertSame(ticket, Ticket.DISCOUNT);
    }

    @Test
    public void getCommonTest() {
        int age = 25;
        Ticket ticket = PaymentDepartment.getTicket(age);

        assertSame(ticket, Ticket.COMMON);
    }
}