package com.ticket.dataStorage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("owner")
public class Owner extends Person {
    private List<Double> account;

    public void addToAccount(double sum){
        account.add(sum);
    }
}
