package com.ticket.ticketproject.dataStorage;

public class TestTicket extends Ticket{
    String name;
    double price;
    String type;
    long id;
    public TestTicket(long id, String name, String type, double price){
        super(id);
        this.name = name;
        this.price = price;
        this.type = type;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Owner getOwner() {
        return null;
    }

    @Override
    public void give() {

    }
    public String getType(){
        return type;
    }
    public double getPrice(){
        return price;
    }
    public long getId(){
        return id;
    }
}
