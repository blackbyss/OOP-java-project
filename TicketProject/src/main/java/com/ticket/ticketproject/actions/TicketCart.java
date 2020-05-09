package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;
@Component
public class TicketCart {

    private List<EventTicket> cart; //Ostukorv
    private List<EventTicket>purchased; //FIXME: Ei tea veel kas läheb tarvis.
    private double cartPrice;
    @Autowired
    private Client client;

    public TicketCart(){}


    public TicketCart(Client client){
        this.client = client;
        this.cart = client.getCart();
        this.purchased = client.getPurchased();
    }
    public void addToCart(EventTicket ticket) {
        cartPrice += ticket.getPrice();
        cart.add(ticket);
        client.setCart(cart);
    }
    public void removeFromCart(EventTicket ticket){
        cartPrice-= ticket.getPrice();
        cart.remove(ticket);
        client.setCart(cart);
    }
    public void clearCart(){
        cart.clear();
        cartPrice = 0;
        client.setCart(cart);
    }
    //Maksmise meetod.
    public boolean buy(EventTicket ticket,OwnerService os){
       Owner owner= os.getByEventID(ticket.getEventID());

        double sum= ticket.getPrice();
        double accountBalance = client.getAccountBalance();
        if(accountBalance >= sum){
            accountBalance -= sum;

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
            // Owner thisOwner = ownerService.getByEventID(cart.get(0).getEventID());
            accountBalance -= cartPrice;
            client.setAccountBalance(accountBalance);
            // thisOwner.addToAccount(cartPrice);
            StringBuilder cartSum = new StringBuilder();
            for (int i = 0; i < cart.size(); i++) {
                cartSum.append(cart.get(i).getName()).append("\t");
                purchased.add(cart.get(i));
            }
            clearCart();
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
