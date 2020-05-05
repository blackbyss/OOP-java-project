package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class TicketCart {

    private List<EventTicket> cart; //Ostukorv
    private List<EventTicket>purchased; //FIXME: Ei tea veel kas läheb tarvis.
    private double cartPrice;
    private Client client;
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
    public boolean buy(EventTicket ticket, Owner thisOwner){

        double sum= ticket.getPrice();
        double accountBalance = client.getAccountBalance();
        if(accountBalance >= sum){
            accountBalance -= sum;
            // Owner thisOwner = ownerService.getByEventID(ticket.getEventID()); //FIXME: Lihtsalt ajutine asendus, et H2 tabelid luua.
            // thisOwner.addToAccount(sum);
            thisOwner.addLog(client.getName()+" "+client.getFamilyName()+" ostis: " +ticket.getName()+ " hinna eest: "+sum);

            // purchased.add(ticket);
            // addToCart(ticket);
            client.setAccountBalance(accountBalance);
            client.addLog("Ostmine õnnestus: " +ticket.getName());
            return true;
        }else{
            //TODO: Teavitamine webappis.
            client.addLog("Ostmine ebaõnnestus: Kontol pole piisavalt raha!");
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
            thisOwner.addLog(client.getName() + " " + client.getFamilyName() + " ostis: " + cartSum + " hinna eest: " + cartPrice);
            client.addLog("Ostmine õnnestus: " + cartSum);
            clearCart();
            return true;
        } else {
            if(cart.isEmpty())
                client.addLog("Ostmine ebaõnnestus: Ostukäru on tühi!");
            else {
                client.addLog("Ostmine ebaõnnestus: Kontol pole piisavalt raha!");
            }
            return false;
        }
    }

}
