package cinema.patterns;

import cinema.exceptions.PaymentException;

public class CardPayment implements PaymentStrategy {
    private String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) throws PaymentException {
        if (amount > 500) {
            // Імітація помилки оплати
            throw new PaymentException("Перевищено ліміт для оплати картою!");
        }

        System.out.println(" Оплата картою " + cardNumber.substring(0, 4) + "XXXX...");
        System.out.println(" Сума " + amount + " грн. успішно списана.");
    }
}