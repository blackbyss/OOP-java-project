package com.ticket.ticketproject.Classes;

import java.util.ArrayList;
import java.util.List;

public class Owner extends Person {
    private List<Double> account;

    public void addToAccount(double sum){
        account.add(sum);
    }
}
