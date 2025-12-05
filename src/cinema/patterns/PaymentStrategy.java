package cinema.patterns;

import cinema.exceptions.PaymentException;

// Інтерфейс для різних методів оплати
public interface PaymentStrategy {

    // Метод для виконання оплати
    void pay(double amount) throws PaymentException;
}