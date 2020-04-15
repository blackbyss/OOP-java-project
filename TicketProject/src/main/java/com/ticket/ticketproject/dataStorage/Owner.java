package com.ticket.ticketproject.dataStorage;

import java.util.ArrayList;
import java.util.List;

public class Owner extends Person {
    public Owner(String name, String familyName, String gender, int age, String email, String iban, String address, String county, long index) {
        super(name, familyName, gender, age, email, iban, address, county, index);
        account = new ArrayList<>();
    }

    private List<Double> account;

    public void addToAccount(double sum){
        account.add(sum);
    }
}
