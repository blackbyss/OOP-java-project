package com.ticket.ticketproject.dataStorage;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class EventTicket extends Ticket implements Storage {

    long eventID;
    long ticketType;
    @Transient
    long amount;
    @Transient
    Event event; //piletile vastav üritus


    public EventTicket() {
    }


    //Getters and Setters

    public long getEventID() {
        return eventID;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }

    public long getTicketType() {
        return ticketType;
    }

    public void setTicketType(long ticketType) {
        this.ticketType = ticketType;
    }
}
