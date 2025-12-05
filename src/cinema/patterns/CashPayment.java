package cinema.patterns;

import cinema.exceptions.PaymentException;

public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) throws PaymentException {
        // Готівка завжди проходить, якщо сума не нульова
        if (amount <= 0) {
            throw new PaymentException("Сума для оплати готівкою не може бути нульовою.");
        }
        System.out.println(" Оплата готівкою.");
        System.out.println(" Будь ласка, внесіть " + amount + " грн. в касу.");
    }
}