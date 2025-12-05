package cinema.services;

import cinema.entities.*;
import cinema.exceptions.*;
import cinema.patterns.*;

// Керує логікою бронювання
public class BookingService {

    private CinemaDatabase db = CinemaDatabase.getInstance();

    public Booking makeBooking(Session session, Client client, int seat, String ticketType, PaymentStrategy paymentMethod)
            throws BookingException, PaymentException {

        Hall hall = session.getHall();

        // 1. Перевірка місця
        if (hall.checkSeat(seat)) {
            throw new BookingException("Місце " + seat + " у залі " + hall.getName() + " вже зайняте!");
        }

        // 2. Створення квитка через Фабрику
        Ticket ticket = TicketFactory.createTicket(ticketType, session.getBaseTicketPrice());
        double finalPrice = ticket.calculateFinalPrice();

        // 3. Оплата (використовуємо Стратегію)
        System.out.println(" Початок оплати ");
        paymentMethod.pay(finalPrice);
        System.out.println(" Оплата успішна! ");

        // 4. Фіналізація
        hall.bookSeat(seat);
        Booking booking = new Booking(session, client, seat, ticket);

        // 5. Збереження в Базі даних
        db.saveBooking(booking.getBookingDetails());

        System.out.println("Бронювання завершено. Видано квиток: " + ticket.getType());
        return booking;
    }
}