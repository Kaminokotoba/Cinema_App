// src/cinema/TestRunner.java
package cinema;

import cinema.entities.*;
import cinema.exceptions.*;
import cinema.patterns.*;
import cinema.services.BookingService;
import cinema.services.ScheduleService;

import java.time.LocalDateTime;

public class TestRunner {

    //Об'єкти для тестування
    private static final BookingService bookingService = new BookingService();
    private static final ScheduleService scheduleService = new ScheduleService();
    private static final Movie testMovie = new Movie("Test Movie", 90); // 90 хвилин
    private static final Movie overlappingMovie = new Movie("Overlap Movie", 120); // 120 хвилин
    private static final Hall testHall = new Hall("Test Hall", 10);
    private static Session testSession;
    private static final Client testClient = new Client("Test Client", "test@mail.com");
    private static final PaymentStrategy cashPayment = new CashPayment();


    public static void main(String[] args) {
        System.out.println("--- ЗАПУСК РУЧНОГО ТЕСТУВАННЯ ---");

        // 0. Налаштування (створення першого сеансу)
        testCase0_Setup();

        // 1. Успішні тести
        testCase1_SuccessfulBooking();

        // 2. Негативні тести (Виключення)
        testCase2_BookingException();
        testCase4_SessionOverlapValidation(); // Тест на перетин часу

        // 3. Тести на Шаблони
        testCase3_PatternCheck();

        System.out.println("\n--- ТЕСТУВАННЯ ЗАВЕРШЕНО ---");
    }

    private static void testCase0_Setup() {
        System.out.println("\n[SETUP] Створення тестового сеансу...");
        try {
            // Створення чистого сеансу, який починається о 14:00 (закінчується о 15:30)
            testSession = scheduleService.createSession(testMovie, testHall, LocalDateTime.now().withHour(14).withMinute(0));
            System.out.println(" Сеанс A (для бронювання) створено успішно (14:00 - " + testSession.getEndTime().toLocalTime() + ").");
        } catch (BookingException e) {
            System.err.println(" ПОМИЛКА SETUP: Не вдалося створити сеанс: " + e.getMessage());
        }
    }

    private static void testCase1_SuccessfulBooking() {
        System.out.println("\n[ТЕСТ 1] Перевірка успішного бронювання (Місце 2)...");
        try {
            bookingService.makeBooking(
                    testSession, testClient, 2, "Standard", cashPayment
            );
            System.out.println(" УСПІХ: Бронювання на місце 2 створено.");

        } catch (CinemaException e) {
            System.err.println(" ПОМИЛКА: Тест 1 не мав кидати виключення: " + e.getMessage());
        }
    }

    private static void testCase2_BookingException() {
        System.out.println("\n[ТЕСТ 2] Перевірка виключення (займе місце 2)...");
        try {
            // Місце 2 вже зайняте Тестом 1
            bookingService.makeBooking(
                    testSession, testClient, 2, "Standard", cashPayment
            );

            System.err.println(" ПОМИЛКА: Тест 2 повинен був кинути BookingException, але не кинув!");

        } catch (BookingException e) {
            System.out.println(" УСПІХ: Отримано очікуване виключення: " + e.getMessage());
        } catch (PaymentException e) {
            System.err.println(" ПОМИЛКА: Отримано невірне виключення PaymentException: " + e.getMessage());
        }
    }

    private static void testCase3_PatternCheck() {
        System.out.println("\n[ТЕСТ 3] Перевірка роботи шаблонів (Singleton та Factory)...");

        CinemaDatabase db1 = CinemaDatabase.getInstance();
        CinemaDatabase db2 = CinemaDatabase.getInstance();
        if (db1 == db2) {
            System.out.println(" УСПІХ: Singleton (CinemaDatabase) працює коректно");
        } else {
            System.err.println(" ПОМИЛКА: Singleton повертає різні екземпляри");
        }

        Ticket vipTicket = TicketFactory.createTicket("VIP", 100.0);
        if (vipTicket.calculateFinalPrice() == 120.0) {
            System.out.println(" УСПІХ: Factory Method створив VIP-квиток з правильною ціною (120.0)");
        } else {
            System.err.println(" ПОМИЛКА: Factory Method розрахував неправильну ціну: " + vipTicket.calculateFinalPrice());
        }
    }

    private static void testCase4_SessionOverlapValidation() {
        System.out.println("\n[ТЕСТ 4] Перевірка валідації перетину сеансів...");

        // Сеанс A закінчується о 15:30

        // Спроба створити сеанс B, який починається о 15:00 і триває 120 хв
        // Це перетинається (15:00 < 15:30), тому має бути помилка
        LocalDateTime overlappingTime = LocalDateTime.now().withHour(15).withMinute(0);

        try {
            System.out.println("Спроба створити пересічний сеанс B о 15:00...");
            scheduleService.createSession(overlappingMovie, testHall, overlappingTime);
            System.err.println(" ПОМИЛКА: Сеанс B був створений, хоча час перетинається");
        } catch (BookingException e) {

            System.out.println(" УСПІХ: Отримано очікуване виключення: " + e.getMessage());
        }
    }
}