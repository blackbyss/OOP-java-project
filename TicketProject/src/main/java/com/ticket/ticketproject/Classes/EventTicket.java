package com.ticket.ticketproject.Classes;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class EventTicket extends Ticket  {

    Enum ticketType; //seisukoht või istekoht
    String place; //loož, rida, koht
    long event_id;
    String buyer;
    long amount;

    @Transient
    Event event; //piletile vastav üritus
    @Transient
    Owner owner;


    @Override
    public Owner getOwner() {
        return owner;
    }

    @Override
    public void give() {   //TODO!: PDF genereerimine ja meilile saatmine siia , kui yes-mail==true;
        //Võiks kuvada ka ekraanil ostetud piletit.
    }


    //Getters and Setters

    public Enum getTicketType() {
        return ticketType;
    }

    public void setTicketType(Enum ticketType) {
        this.ticketType = ticketType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
