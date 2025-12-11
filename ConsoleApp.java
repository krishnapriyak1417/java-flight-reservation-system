import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    public static void main(String[] args) {
        IFlightService service = new FlightService();
        seedFlights(service);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== Flight Reservation System ===");
                System.out.println("1. Search for flights");
                System.out.println("2. Book a flight");
                System.out.println("3. View my reservations");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> searchFlights(scanner, service);
                    case "2" -> bookFlight(scanner, service);
                    case "3" -> viewReservations(scanner, service);
                    case "4" -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please enter 1–4.");
                }
            }
        }
    }

    private static void seedFlights(IFlightService service) {
        // A few sample flights so the console UI is easy to try out.
        service.addFlight(new Flight(
                "AA101",
                "Chicago",
                LocalDateTime.now().plusDays(1).withHour(9).withMinute(30),
                10
        ));
        service.addFlight(new Flight(
                "DL202",
                "New York",
                LocalDateTime.now().plusDays(1).withHour(14).withMinute(15),
                5
        ));
        service.addFlight(new Flight(
                "UA303",
                "Chicago",
                LocalDateTime.now().plusDays(2).withHour(18).withMinute(0),
                20
        ));
    }

    private static void searchFlights(Scanner scanner, IFlightService service) {
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine().trim();

        System.out.print("Enter travel date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine().trim();

        try {
            LocalDate date = LocalDate.parse(dateStr);
            LocalDateTime atStartOfDay = date.atStartOfDay();

            List<Flight> results = service.searchFlights(destination, atStartOfDay);

            if (results.isEmpty()) {
                System.out.println("No flights found for " + destination + " on " + date + ".");
            } else {
                System.out.println("\nAvailable flights:");
                for (Flight f : results) {
                    System.out.println(" - " + f);
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void bookFlight(Scanner scanner, IFlightService service) {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine().trim();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine().trim();

        System.out.print("Enter travel date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine().trim();

        try {
            LocalDate date = LocalDate.parse(dateStr);
            LocalDateTime atStartOfDay = date.atStartOfDay();

            List<Flight> results = service.searchFlights(destination, atStartOfDay);

            if (results.isEmpty()) {
                System.out.println("No flights found to book.");
                return;
            }

            System.out.println("\nMatching flights:");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ") " + results.get(i));
            }

            System.out.print("Choose a flight number (1-" + results.size() + "): ");
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (index < 0 || index >= results.size()) {
                System.out.println("Invalid selection.");
                return;
            }

            Flight selected = results.get(index);

            System.out.print("How many seats would you like to book? ");
            int seats = Integer.parseInt(scanner.nextLine().trim());

            try {
                Reservation reservation = service.bookFlight(customerName, selected, seats);
                System.out.println("\nBooking successful:");
                System.out.println(" " + reservation);
            } catch (IllegalArgumentException ex) {
                System.out.println("Could not complete booking: " + ex.getMessage());
            }

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter numeric values where required.");
        }
    }

    private static void viewReservations(Scanner scanner, IFlightService service) {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine().trim();

        List<Reservation> reservations = service.getReservationsForCustomer(customerName);

        if (reservations.isEmpty()) {
            System.out.println("No reservations found for " + customerName + ".");
        } else {
            System.out.println("\nReservations for " + customerName + ":");
            for (Reservation r : reservations) {
                System.out.println(" - " + r);
            }
        }
    }
}
