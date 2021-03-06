package com.ticket.ticketproject.dataStorage;

import javax.persistence.Entity;

@Entity
public class Owner extends Person implements Storage {
    private long eventID;


    public Owner(String name, String familyName, int age, String email, String iban, String address, String county, long index, long eventID, int ticketCount, double accountBalance) {
        super(name, familyName, age, email, iban, address, county, index, accountBalance);
    }

    public Owner() {
    }


    public long getEventID() {
        return eventID;
    }

    public void setEventID(long event_id) {
        this.eventID = event_id;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

}