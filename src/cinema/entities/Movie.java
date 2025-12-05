package cinema.entities;

public class Movie {
    private String title;
    private int durationMinutes;

    public Movie(String title, int durationMinutes) {
        this.title = title;
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() { return durationMinutes; }
    public String getTitle() { return title; }
}