package cinema.entities;

import java.time.LocalDateTime;

public class Session {
    private Movie movie;
    private Hall hall;
    private LocalDateTime startTime;
    private double baseTicketPrice = 80.0;

    public Session(Movie movie, Hall hall, LocalDateTime startTime) {
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return startTime.plusMinutes(movie.getDurationMinutes());
    }

    public Hall getHall() { return hall; }
    public LocalDateTime getStartTime() { return startTime; }
    public Movie getMovie() { return movie; }
    public double getBaseTicketPrice() { return baseTicketPrice; }
    public void setBaseTicketPrice(double baseTicketPrice) { this.baseTicketPrice = baseTicketPrice; }
}