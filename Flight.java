import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {

    private final String flightNumber;
    private final String destination;
    private final LocalDateTime departureTime;
    private int availableSeats;

    public Flight(String flightNumber,
                  String destination,
                  LocalDateTime departureTime,
                  int availableSeats) {

        if (flightNumber == null || flightNumber.isBlank()) {
            throw new IllegalArgumentException("flightNumber cannot be blank");
        }
        if (destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("destination cannot be blank");
        }
        if (departureTime == null) {
            throw new IllegalArgumentException("departureTime cannot be null");
        }
        if (availableSeats < 0) {
            throw new IllegalArgumentException("availableSeats cannot be negative");
        }

        this.flightNumber = flightNumber;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    //Decrease the number of available seats for this flight.
     
    public void bookSeats(int seatsToBook) {
        if (seatsToBook <= 0) {
            throw new IllegalArgumentException("Seats to book must be positive");
        }
        if (seatsToBook > availableSeats) {
            throw new IllegalArgumentException("Not enough seats available");
        }
        availableSeats -= seatsToBook;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[" + flightNumber + "] " + destination +
                " at " + departureTime.format(fmt) +
                " | seats left: " + availableSeats;
    }
}
