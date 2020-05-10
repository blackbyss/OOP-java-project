package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.Client;
import com.ticket.ticketproject.dataStorage.EventTicket;
import com.ticket.ticketproject.dataStorage.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketCart {

    private List<EventTicket> cart; //Ostukorv
    private double cartPrice;
    @Autowired
    private Client client;

    public TicketCart() {
        this.cart = new ArrayList<>();
    }


    public void addToCart(EventTicket ticket) {
        cartPrice += ticket.getPrice();
        cart.add(ticket);
    }

    public void removeByIndex(int i) {
        EventTicket ticket = cart.get(i);
        cartPrice -= ticket.getPrice();
        cart.remove(i);
    }

    public void clearCart() {
        cart.clear();
        cartPrice = 0;
    }
    //Maksmise meetod.

    public boolean buyAll(OwnerService os) {
        double accountBalance = client.getAccountBalance();
        if (accountBalance >= cartPrice && !cart.isEmpty()) {
            for (EventTicket ticket : cart) {
                Owner owner = os.getByEventID(ticket.getEventID());
                double sum = ticket.getPrice();
                double ownerbalance = owner.getAccountBalance();
                owner.setAccountBalance(ownerbalance + sum);

            }
            accountBalance -= cartPrice;
            client.setAccountBalance(accountBalance);
            return true;
        } else {
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
