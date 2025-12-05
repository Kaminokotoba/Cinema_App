package cinema.exceptions;

// Помилка під час бронювання (наприклад, місце зайняте)
public class BookingException extends CinemaException {
    public BookingException(String message) {
        super(message);
    }
}