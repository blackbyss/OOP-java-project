# OOP-java-project

Java ticket system. Operates as a Spring Boot MVC application with embedded H2(Hibernate dialect).

Current project plan: 
  Ticket sales system where user enters data using HTML forms.
  The server uses Java-Mail to send the ticket as PDF to the client's email or allows user to download ticket pdfs as a zip file.
  The program also updates the H2 database with necessary information.
  The user can choose between different events and ticket types that are in the DB. The server saves sales history and clients' information. The project has a built in cart-system that lets user buy tickets of different events and types. Also the system checks that clients age is sufficient to buy tickets for certain events and if client has enough funds on his/her bank account. The program also keeps track how many tickets are left and if last tickets for an event is bought then the sales for the event are stopped.
  
  Project status: finished
  

  # Quick-Setup

  Open TicketProject as a Maven project in your IDE ----> Wait for dependecies to reslove ----> 
  Run TicketProjectApplication ----> Open browser at localhost:8080.
