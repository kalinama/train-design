package car.passenger;

public enum Ticket {
    DISCOUNT(50),
    COMMON(100);

    private final int cost;

    Ticket(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
