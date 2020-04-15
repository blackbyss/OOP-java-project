package com.ticket.dataStorage;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Ticket implements Storage {


    @Id
    @GeneratedValue
    private long id;
   private double price;

    public Ticket(double price) {
        this.price=price;
    }

    public Ticket() {
    }


    public double getPrice() {
        return price;
    }

    public abstract String getName();
    public abstract Owner getOwner();
    public abstract void give();
}
