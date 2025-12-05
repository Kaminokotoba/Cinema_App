package cinema.entities;

// Клас для опису кінозалу
public class Hall {
    private String name;
    private int totalSeats;
    private boolean[] isSeatBooked; // Простий масив для стану місць

    public Hall(String name, int totalSeats) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.isSeatBooked = new boolean[totalSeats + 1]; // Місця з 1
    }

    public String getName() { return name; }
    public int getTotalSeats() { return totalSeats; }

    public boolean checkSeat(int seatNumber) {
        if (seatNumber <= 0 || seatNumber > totalSeats) return false;
        return isSeatBooked[seatNumber];
    }

    public void bookSeat(int seatNumber) {
        if (seatNumber > 0 && seatNumber <= totalSeats) {
            isSeatBooked[seatNumber] = true;
        }
    }
}