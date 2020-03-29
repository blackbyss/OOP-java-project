# OOP-java-project

Java ticket system. Currently using Maven and Apache Tomcat library for web application

Current project plan: 
  Ticket sales system where user enters info into tomcat web application.
  The server creates a QR code with that info and sends that QR code to the users entered email.
  Server will remember that QR code and track it's usage for statistical purposes.
  User info will be secured.
  Intending to make it multithreaded so server can handle multiple users at once.

  # Quick-Setup

  Open TicketProject as a Maven project in your IDE ----> Add Tomcat 9.0.33 as Configuration ----> Run

  In HTML form make sure to fill all fields before submit, the "required" tag only works with Chrome atm.
