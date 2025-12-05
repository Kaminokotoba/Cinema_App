package cinema.patterns;

// Клас PUBLIC, щоб його можна було використовувати в Booking.java
public abstract class Ticket {
    protected double basePrice;

    public abstract String getType();
    public abstract double calculateFinalPrice();
}