# Remote Calculator

### Objective of project

To consolidate understanding of Client - server architecture in Java using TCP.

### Outline of Project:

Client.java:
- Implements runnable
- Continuously instantiates multiple Client objects and runs them to simulate a many-to-one server architecture
- Each instantiation:
-   Connects to the server socket
-   Prompts the user for a calculation
-   Sends the calculation to the server
-   Awaits the response and prints the result

Calculations are In the form of "<OPERATION> <num1> <num2>" where OPERATION is either + or *, num1 and num2 are single digit numbers.
> Note that no edge case handling has been implemented here to check if that the input format is correct, as this project was an exercise to test my understanding of the server implementation, not the calculator!

Server.java:
- Contains an execute cycle which:
-   Continuously waits for TCP socket requests.
-   For each request, sets up a new Service class, passing in the client socket and "forgets" about it.

> Note that this architecture is not optimal for many clients, due to the overhead of setting up a new thread for each request.

Service.java:
- Has three main jobs:
-   Interprets the request by removing terminating character ("#") and splitting by " ".
-   Computes the request.
-   Returns the value to the client socket.
