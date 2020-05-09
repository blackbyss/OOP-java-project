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
    int ticketCount;
    @Transient
    private List<EventTicket> cart; //Ostukorv
    @Transient
    private List<EventTicket>purchased; //FIXME: Ei tea veel kas läheb tarvis.

    public Client(String eesnimi, String perenimi, int vanus, String email, String iban, String aadress, String maakond, long indeks, boolean yes_mail, int ticketCount, double accountBalance) {
        super(eesnimi, perenimi, vanus, email, iban, aadress, maakond, indeks,ticketCount, accountBalance);
        this.yes_mail = yes_mail;
        cart=new ArrayList<EventTicket>();
        purchased=new ArrayList<EventTicket>();
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

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public List<EventTicket> getCart() {
        return cart;
    }

    public void setCart(List<EventTicket> cart) {
        this.cart = cart;
    }

    public List<EventTicket> getPurchased() {
        return purchased;
    }

    public void setPurchased(List<EventTicket> purchased) {
        this.purchased = purchased;
    }
}