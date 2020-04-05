package com.ticket.ticketproject.Classes;

import com.ticket.ticketproject.Classes.Owner;

import java.util.Date;

public class Event {
    private String location;
    private int capacity; // mitu inimest mahub kokku
   // private Date aeg;
    private Owner owner;

    public Event(String location, int capacity/*, Date aeg */, Owner owner) {
        this.location = location;
        this.capacity = capacity;
       // this.aeg = aeg;
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }
}
