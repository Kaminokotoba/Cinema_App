package cinema.services;

import cinema.exceptions.PaymentException;
import cinema.patterns.PaymentStrategy;

// Сервіс для управління всіма фінансовими операціями
public class PaymentService {

    // Може приймати будь-яку спосіб оплати (Поліморфізм)
    public boolean processPayment(double amount, PaymentStrategy strategy) {
        try {
            strategy.pay(amount);
            return true;
        } catch (PaymentException e) {
            System.err.println("ПОМИЛКА ОПЛАТИ: " + e.getMessage());
            return false;
        }
    }
}