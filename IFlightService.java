import java.time.LocalDateTime;
import java.util.List;

// Simple service layer abstraction for managing flights and reservations.
 
public interface IFlightService {

    void addFlight(Flight flight);

    List<Flight> getAllFlights();

    List<Reservation> getAllReservations();

    List<Flight> searchFlights(String destination, LocalDateTime dateTime);

    Reservation bookFlight(String customerName, Flight flight, int seats);

    List<Reservation> getReservationsForCustomer(String customerName);
}
