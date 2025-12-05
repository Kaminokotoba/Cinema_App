package cinema.exceptions;

// Головна помилка кінотеатру
public class CinemaException extends Exception {
    public CinemaException(String message) {
        super(message);
    }
}