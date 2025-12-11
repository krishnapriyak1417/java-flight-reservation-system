import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//In-memory implementation of the flight service.
 //For the exercise, this just keeps everything in lists.
 
public class FlightService implements IFlightService {

    private final List<Flight> flights = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    @Override
    public void addFlight(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("flight cannot be null");
        }
        flights.add(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
        return Collections.unmodifiableList(flights);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return Collections.unmodifiableList(reservations);
    }

    @Override
    public List<Flight> searchFlights(String destination, LocalDateTime dateTime) {
        if (destination == null || destination.isBlank() || dateTime == null) {
            return Collections.emptyList();
        }

        LocalDate requestedDate = dateTime.toLocalDate();
        List<Flight> matches = new ArrayList<>();

        for (Flight flight : flights) {
            boolean sameDestination =
                    flight.getDestination().equalsIgnoreCase(destination);
            boolean sameDate =
                    flight.getDepartureTime().toLocalDate().isEqual(requestedDate);
            boolean hasSeats = flight.getAvailableSeats() > 0;

            if (sameDestination && sameDate && hasSeats) {
                matches.add(flight);
            }
        }

        return matches;
    }

    @Override
    public Reservation bookFlight(String customerName, Flight flight, int seats) {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("customerName cannot be blank");
        }
        if (flight == null) {
            throw new IllegalArgumentException("flight cannot be null");
        }
        if (!flights.contains(flight)) {
            throw new IllegalArgumentException("Unknown flight");
        }

        // Let the Flight enforce its own seat rules.
        flight.bookSeats(seats);

        Reservation reservation = new Reservation(customerName, flight, seats);
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> getReservationsForCustomer(String customerName) {
        if (customerName == null || customerName.isBlank()) {
            return Collections.emptyList();
        }

        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomerName().equalsIgnoreCase(customerName)) {
                result.add(reservation);
            }
        }
        return result;
    }
}
