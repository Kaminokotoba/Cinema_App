package cinema.exceptions;

// Помилка під час оплати
public class PaymentException extends CinemaException {
    public PaymentException(String message) {
        super(message);
    }
}