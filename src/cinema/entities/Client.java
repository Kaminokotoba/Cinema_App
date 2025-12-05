package cinema.entities;

// Клас клієнта (Наслідування)
public class Client extends User {

    public Client(String name, String email) {
        super(name, email);
    }

    @Override
    public String getRole() {
        return "Клієнт";
    }
}