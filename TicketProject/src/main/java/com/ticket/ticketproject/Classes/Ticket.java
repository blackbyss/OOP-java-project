package com.ticket.ticketproject.Classes;

public abstract class Ticket {

   private String name;
    private double price;

    public Ticket(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract Owner getOwner();

    public abstract void give();

}
