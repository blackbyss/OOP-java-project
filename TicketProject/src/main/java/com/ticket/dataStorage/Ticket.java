package com.ticket.dataStorage;

import com.ticket.dataStorage.Owner;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Ticket {
    @Id
    @GeneratedValue
    private long id;
   private String name;
   private double price;



    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract Owner getOwner();
    public abstract void give();

}
