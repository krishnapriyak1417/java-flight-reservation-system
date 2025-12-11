public class Reservation {

    private final String customerName;
    private final Flight flight;
    private final int seatsBooked;

    public Reservation(String customerName, Flight flight, int seatsBooked) {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("customerName cannot be blank");
        }
        if (flight == null) {
            throw new IllegalArgumentException("flight cannot be null");
        }
        if (seatsBooked <= 0) {
            throw new IllegalArgumentException("seatsBooked must be positive");
        }

        this.customerName = customerName;
        this.flight = flight;
        this.seatsBooked = seatsBooked;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customerName='" + customerName + '\'' +
                ", flight=" + flight +
                ", seatsBooked=" + seatsBooked +
                '}';
    }
}



