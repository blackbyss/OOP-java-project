package com.ticket.ticketproject.dataStorage;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Owner extends Person implements Storage {

    private long event_id;

    public Owner(String name, String familyName, String gender, int age, String email, String iban, String address, String county, long index, long event_id) {
        super(name, familyName, age, email, iban, address, county, index);
        account = new ArrayList<>();
    }
    public Owner() {
    }
    @Transient
    private List<Double> account;


    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
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
