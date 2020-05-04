package com.ticket.ticketproject.dataStorage;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Owner extends Person implements Storage {
    private long eventID;


    public Owner(String name, String familyName, int age, String email, String iban, String address, String county, long index, long eventID) {
        super(name, familyName, age, email, iban, address, county, index);
        account = new ArrayList<>();
    }
    public Owner() {
    }

    @Transient
    private List<Double> account;


    public long getEventID() {
        return eventID;
    }

    public void setEventID(long event_id) {
        this.eventID = event_id;
    }

    public List<Double> getAccount() {
        return account;
    }

    public void setAccount(List<Double> account) {
        this.account = account;
    }

    public void addToAccount(double sum){
        account.add(sum);
    }
}
