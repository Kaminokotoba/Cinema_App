package cinema.entities;
import cinema.patterns.Ticket;

public class Booking {
    private Session session;
    private Client client;
    private int seatNumber;
    private Ticket ticket;

    public Booking(Session session, Client client, int seatNumber, Ticket ticket) {
        this.session = session;
        this.client = client;
        this.seatNumber = seatNumber;
        this.ticket = ticket;
    }

    public String getBookingDetails() {
        return String.format("Фільм: %s, Зал: %s, Місце: %d, Квиток: %s, Клієнт: %s",
                session.getMovie().getTitle(),
                session.getHall().getName(),
                seatNumber,
                ticket.getType(),
                client.getName());
    }
}