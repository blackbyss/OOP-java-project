package com.ticket.ticketproject.dataStorage;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
public class Client extends Person implements Storage {
    boolean yes_mail; //Kas soovib emailiga piletit?


    public Client(String eesnimi, String perenimi, int vanus, String email, String iban, String aadress, String maakond, long indeks, boolean yes_mail,double accountBalance) {
        super(eesnimi, perenimi, vanus, email, iban, aadress, maakond, indeks,accountBalance);
        this.yes_mail = yes_mail;
    }
    public Client(){
    }

    public String clientToString(){
        String s= this.toString();

        return s+" "+ yes_mail;
    }

    public boolean isYes_mail() {
        return yes_mail;
    }

    public void setYes_mail(boolean yes_mail) {
        this.yes_mail = yes_mail;
    }


}