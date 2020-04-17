package com.ticket.ticketproject.dataStorage;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class EventTicket extends Ticket implements Storage {

    long ticketType; //seisukoht või istekoht
    String place; //loož, rida, koht
    long eventID;

    @Transient
    Event event; //piletile vastav üritus




    public EventTicket( double price, Event event) {
        super(price);
        this.event=event;
    }

    public EventTicket() {
    }


    @Override
    public Owner getOwner() {
        return event.getOwner();
    }

    @Override
    public void give() {   //TODO!: PDF genereerimine ja meilile saatmine siia , kui yes-mail==true;
        //Võiks kuvada ka ekraanil ostetud piletit.
    }


    //Getters and Setters

    public long getTicketType() {
        return ticketType;
    }

    public void setTicketType(long ticketType) {
        this.ticketType = ticketType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getEventID() {
        return eventID;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
