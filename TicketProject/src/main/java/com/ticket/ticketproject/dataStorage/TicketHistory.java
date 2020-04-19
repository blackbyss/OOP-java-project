package com.ticket.ticketproject.dataStorage;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TicketHistory {
    @Id
    long ticket_id;
    String name;
    String last_name;
    double price;
    LocalDate time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public TicketHistory() {
    }
    public  TicketHistory(long ticket_id, String name, String last_name, double price){
      this.ticket_id = ticket_id;
      this.name = name;
      this.last_name = last_name;
      this.price = price;
      time = LocalDate.now();
    }

}
