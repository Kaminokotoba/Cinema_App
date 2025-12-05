package cinema.patterns;

public class TicketFactory {

    public static Ticket createTicket(String type, double basePrice) {
        if (type.equalsIgnoreCase("VIP")) {
            // Створює об'єкт VipTicket
            return new VipTicket(basePrice);
        } else {
            return new StandardTicket(basePrice);
        }
    }
}