package cinema.patterns;

public class StandardTicket extends Ticket {
    public StandardTicket(double price) { this.basePrice = price; }

    @Override
    public String getType() { return "STANDARD"; }

    @Override
    public double calculateFinalPrice() { return basePrice; }
}