package cinema.services;

import cinema.entities.Hall;
import cinema.entities.Movie;
import cinema.entities.Session;
import cinema.exceptions.BookingException;
import cinema.patterns.CinemaDatabase;

import java.time.LocalDateTime;

public class ScheduleService {

    private final CinemaDatabase db = CinemaDatabase.getInstance();

    public Session createSession(Movie movie, Hall hall, LocalDateTime startTime)
            throws BookingException {

        Session newSession = new Session(movie, hall, startTime);

        // 1. Перевырка на перетин часу
        if (isTimeSlotOccupied(newSession)) {
            throw new BookingException(
                    "Неможливо створити сеанс! Зал " + hall.getName() +
                            " вже зайнятий в період з " + newSession.getStartTime() +
                            " до " + newSession.getEndTime()
            );
        }

        // 2. Якщо валідація успішна, зберігаємо сеанс
        db.addSession(newSession);
        return newSession;
    }

    private boolean isTimeSlotOccupied(Session newSession) {

        LocalDateTime newStart = newSession.getStartTime();
        LocalDateTime newEnd = newSession.getEndTime();
        String newHallName = newSession.getHall().getName();

        for (Session existingSession : db.getAllSessions()) {

            if (existingSession.getHall().getName().equals(newHallName)) {

                LocalDateTime existingStart = existingSession.getStartTime();
                LocalDateTime existingEnd = existingSession.getEndTime();

                boolean overlap = newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart);

                if (overlap) {
                    return true;
                }
            }
        }
        return false;
    }
}