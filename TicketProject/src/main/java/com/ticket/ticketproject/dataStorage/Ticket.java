package com.ticket.ticketproject.dataStorage;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Ticket implements Storage {


    @Id
    @GeneratedValue
    private long id;
    private String name;
   private double price;


    public Ticket() {
    }


    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
