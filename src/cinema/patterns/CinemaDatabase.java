package cinema.patterns;
import cinema.entities.Session;
import java.util.ArrayList;
import java.util.List;

public class CinemaDatabase {
    private static CinemaDatabase instance;
    private List<String> savedBookings;
    private List<Session> allSessions;

    private CinemaDatabase() {
        savedBookings = new ArrayList<>();
        allSessions = new ArrayList<>();
        System.out.println("База даних: запущена...");
    }

    public static CinemaDatabase getInstance() {
        if (instance == null) { instance = new CinemaDatabase(); }
        return instance;
    }

    public void addSession(Session session) {
        this.allSessions.add(session);
    }

    public List<Session> getAllSessions() {
        return allSessions;
    }

    public void saveBooking(String bookingInfo) {
        savedBookings.add(bookingInfo);
        System.out.println("\t[DB] Бронювання збережено: " + bookingInfo);
    }

    public List<String> getBookings() { return savedBookings; }
}