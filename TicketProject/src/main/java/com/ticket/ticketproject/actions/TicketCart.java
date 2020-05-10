package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Component
public class TicketCart {

    private List<EventTicket> cart; //Ostukorv
    private double cartPrice;
    @Autowired
    private Client client;

    public TicketCart(){
        this.cart = new ArrayList<>();
    }


    public void addToCart(EventTicket ticket) {
        cartPrice += ticket.getPrice();
        cart.add(ticket);
    }
    public void removeFromCart(EventTicket ticket){
        cartPrice-= ticket.getPrice();
        cart.remove(ticket);
    }
    public void removeByIndex(int i){
        EventTicket ticket = cart.get(i);
        cartPrice-= ticket.getPrice();
        cart.remove(i);
    }
    public void clearCart(){
        cart.clear();
        cartPrice = 0;
    }
    //Maksmise meetod.
    public boolean buy(EventTicket ticket,OwnerService os){
       Owner owner= os.getByEventID(ticket.getEventID());

        double sum= ticket.getPrice();
        double accountBalance = client.getAccountBalance();
        if(accountBalance >= sum){
            accountBalance -= sum;
            owner.setAccountBalance(accountBalance);
            client.setAccountBalance(accountBalance);
            return true;
        }else{
            //TODO: Teavitamine webappis.
            return false;
        }
    }
    public boolean buyAll(OwnerService os){
        double accountBalance = client.getAccountBalance();
        if (accountBalance >= cartPrice && !cart.isEmpty()) {
            for (EventTicket ticket:cart) {
                Owner owner= os.getByEventID(ticket.getEventID());
                double sum= ticket.getPrice();
                double ownerbalance = owner.getAccountBalance();
                owner.setAccountBalance(ownerbalance+sum);

            }
            accountBalance -= cartPrice;
            client.setAccountBalance(accountBalance);
            return true;
        } else {
            if(cart.isEmpty()){

            }

            else {

            }
            return false;
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<EventTicket> getCart() {
        return cart;
    }


}
