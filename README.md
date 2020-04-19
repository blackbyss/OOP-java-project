# OOP-java-project

Java ticket system. Currently operates as a Spring Boot MVC application with embedded H2(Hibernate dialect.

Current project plan: 
  Ticket sales system where user enters data using HTML forms.
  The server uses Java-Mail to send the ticket as PDF to the client's email and updates the H2 database with necessary information.
  The user can choose between different events and ticket types that are in the DB. The server saves sales history and clients' information. The sales history is used to determine how many tickets can be sold and the system restricts buying tickets to events that are sold out. Also we are planning to add a cart system for the client to buy multiple tickets to different events and with different ticket types.
  

  # Quick-Setup

  Open TicketProject as a Maven project in your IDE ----> Wait for dependecies to reslove ----> 
  Run TicketProjectApplication ----> Open browser at localhost:8080.

  In HTML form make sure to fill all fields before submit, the "required" tag only works with Chrome atm.
