package cinema;

import cinema.entities.*;
import cinema.exceptions.*;
import cinema.patterns.*;
import cinema.services.*;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== СТАРТ СИСТЕМИ БРОНЮВАННЯ КВИТКІВ ===");

        // 1. Створення початкових сутностей
        Movie dune = new Movie("Dune 2", 160);
        Hall Hall1 = new Hall("Зал 1 (Premium)", 50);

        // Сеанс: Фільм, Зал, Час
        Session session1 = new Session(dune, Hall1, LocalDateTime.of(2025, 12, 5, 19, 30));

        Client client1 = new Client("Олексій Петров", "oleksiy@mail.com");
        Client client2 = new Client("Марія Іваненко", "maria@mail.com");

        BookingService bookingService = new BookingService();
        ReportService reportService = new ReportService();

        System.out.println("\n*** Сеанс: " + session1.toString());

        // СЦЕНАРІЙ 1: Успішне бронювання VIP картою (Strategy, Factory)
        try {
            System.out.println("\n[1] Клієнт 1 бронює VIP місце 5:");
            bookingService.makeBooking(
                    session1,
                    client1,
                    5,
                    "VIP",
                    new CardPayment("4441111122223333") // Strategy: CardPayment
            );
        } catch (CinemaException e) {
            System.err.println("ПОМИЛКА: " + e.getMessage());
        }

        // СЦЕНАРІЙ 2: Успішне бронювання STANDART готівкою
        try {
            System.out.println("\n[2] Клієнт 2 бронює Standart місце 15:");
            bookingService.makeBooking(
                    session1,
                    client2,
                    15,
                    "Standard",
                    new CashPayment() // Strategy: CashPayment
            );
        } catch (CinemaException e) {
            System.err.println("ПОМИЛКА: " + e.getMessage());
        }

        // СЦЕНАРІЙ 3: Помилка - спроба забронювати вже зайняте місце
        try {
            System.out.println("\n[3] Клієнт 3 (просто гість) намагається забронювати місце 5:");
            // Місце 5 вже зайняте клієнтом 1
            bookingService.makeBooking(
                    session1,
                    new Client("Гість", "guest@mail.com"),
                    5,
                    "Standard",
                    new CashPayment()
            );
        } catch (BookingException e) {
            // Очікувана помилка
            System.err.println("ОБРОБКА ПОМИЛКИ: " + e.getMessage());
        } catch (CinemaException e) {
            System.err.println("ЗАГАЛЬНА ПОМИЛКА: " + e.getMessage());
        }

        // Демонстрація Звіту
        reportService.generateDailyReport();
    }
}