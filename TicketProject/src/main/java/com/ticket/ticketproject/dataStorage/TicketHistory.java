package com.ticket.ticketproject.dataStorage;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TicketHistory {
    @Id
    long ticket_id;
    long buyer_id; //Refers to: Client id
    long ticketType; //Refers to: EventTicket id
    LocalDate time;

    public long getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(long buyer_id) {
        this.buyer_id = buyer_id;
    }

    public long getTicketType() {
        return ticketType;
    }

    public void setTicketType(long ticketType) {
        this.ticketType = ticketType;
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


    public TicketHistory() {
    }

    public TicketHistory(long ticket_id) {
        this.ticket_id = ticket_id;
        time = LocalDate.now();
    }

}
