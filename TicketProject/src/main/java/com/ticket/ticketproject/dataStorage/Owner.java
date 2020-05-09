package com.ticket.ticketproject.dataStorage;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner extends Person implements Storage {
    private long eventID;


    public Owner(String name, String familyName, int age, String email, String iban, String address, String county, long index, long eventID,int ticketCount, double accountBalance) {
        super(name, familyName, age, email, iban, address, county, index,ticketCount, accountBalance);
    }

    public Owner() {
    }


    public long getEventID() {
        return eventID;
    }

    public void setEventID(long event_id) {
        this.eventID = event_id;
    }

}