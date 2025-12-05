package cinema.patterns;

public class VipTicket extends Ticket {
    public VipTicket(double price) { this.basePrice = price; }

    @Override
    public String getType() { return "VIP"; }

    @Override
    public double calculateFinalPrice() {
        return basePrice + 20.0; // Додаток ціни
    }
}