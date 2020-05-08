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
    OwnerService os;
    @Autowired
    private Client client;
    public TicketCart(Client client,OwnerService os){
        this.client = client;
        this.cart = client.getCart();
        this.purchased = client.getPurchased();
        this.os=os;
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
    public boolean buy(EventTicket ticket){
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
    public boolean buyAll(Owner thisOwner){
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

}
