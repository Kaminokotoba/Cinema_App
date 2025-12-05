package cinema.services;

import cinema.patterns.CinemaDatabase;

// Сервіс для генерації звітів
public class ReportService {

    private CinemaDatabase db = CinemaDatabase.getInstance(); // Singleton

    public void generateDailyReport() {
        System.out.println("\n--- ЗВІТ ПРОДАЖІВ КІНОТЕАТРУ ---");

        if (db.getBookings().isEmpty()) {
            System.out.println("Сьогодні бронювань ще не було.");
            return;
        }

        System.out.println("Усього продано квитків: " + db.getBookings().size());

        // Виводимо список усіх бронювань
        for (String booking : db.getBookings()) {
            System.out.println("- " + booking);
        }
        System.out.println("---------------------------------");
    }
}