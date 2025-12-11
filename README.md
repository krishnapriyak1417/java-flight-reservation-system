# java-flight-reservation-system
By Krishna Priya Kommineni

This project is my implementation of the Java Engineer assessment task.
It simulates a small flight reservation system where a user can search flights, book seats, and view their reservations, all through a simple console interface.

My goal was to write clean, easy-to-understand code that reflects real engineering habits: separating responsibilities, preventing invalid actions, and making the program easy to modify or extend.

How to Run the Application
Requirements:
  > Java 17+ installed

Compile the source files
From the project root:
"javac src/main/java/*.java"

Run the application
"java -cp src/main/java ConsoleApp"

The program will launch an interactive menu where users can:

 > Search flights by destination and date
 > Book seats
 > View all reservations made under a name

Design Decisions
1. Clear Separation of Responsibilities

I split the code into simple, focused classes:

  >Flight holds flight information
  >Reservation represents a user’s booking
  >FlightService contains all business logic (searching + booking)
  >ConsoleApp handles user interaction only

This separation makes the code easier to maintain and test.

2. In-Memory Lists

The requirement didn’t call for file or database storage, so I used basic in-memory List collections.
This keeps the program simple while still reflecting how a real service layer retrieves and stores domain objects.

3. Validation for Realistic Behavior

Even in a small system, I added checks that mimic real-world logic:

  >Prevent booking more seats than available
  >Handle empty search results
  >Validate user input as much as possible

These small details keep the system predictable and safe.

4. Console Flow Designed to Be Beginner Friendly

I tried to keep the menu and prompts clear so anyone running the program can understand what to do, even without technical experience.

5. Added an Interface for the Service Layer

I included a small interface (IFlightService) so the service logic is not tightly coupled.
This reflects professional development patterns where implementations may change (e.g., switching to a database or API later).

Real-Life Considerations
1. Overbooking Prevention

Real airline systems must be very strict about seat availability.
My implementation blocks any booking attempt that would reduce seats below zero.

2. Search Behavior

Flights are searched by:

>Destination
>Matching date (day-level match, not time)

In real systems, this logic would consider:

>Time zones
>Flight duration
>Airport codes

But the simplified version still demonstrates the structure required.

3. Extensibility

The way the service is structured allows easy future enhancements, such as:

1.Adding pricing
2.Canceling reservations
3.Tracking passengers
4.Integrating with an external data source

This mirrors how a real production system gradually grows.

4. Testing Approach

Although the environment didn't require an executable Maven/JUnit setup,
I wrote JUnit-style tests for:

1.Booking valid seats
2.Preventing overbooking
3.Searching flights with no results
4.Listing reservations for a specific customer

This shows intentional testing thought and how I verify business logic.

Final Notes

This project reflects how I approach real engineering problems, by organizing code cleanly, validating inputs, thinking about future changes, and designing small systems that behave predictably.
