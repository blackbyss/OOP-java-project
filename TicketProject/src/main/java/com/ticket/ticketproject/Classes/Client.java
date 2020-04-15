package com.ticket.ticketproject.Classes;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    boolean yes_mail; //Kas soovib emailiga piletit?
    private double accountBalance; //Konto
    private List<Ticket> cart; //Ostukorv
    private double cartPrice;
    private List<Ticket>purchased; //FIXME: Ei tea veel kas läheb tarvis.

    public Client(String eesnimi, String perenimi, String sugu, int vanus, String email, long telefon, String aadress, String maakond, long indeks, boolean yes_mail,double accountBalance) {
        super(eesnimi, perenimi, sugu, vanus, email, telefon, aadress, maakond, indeks);
        this.yes_mail = yes_mail;
        this.accountBalance=accountBalance;
        cart=new ArrayList<Ticket>();
        purchased=new ArrayList<Ticket>();
    }
    public void addToList(Ticket ticket) {
        cartPrice += ticket.getPrice();
        cart.add(ticket);
    }
    public void removeFromList(Ticket ticket){
        cartPrice-= ticket.getPrice();
        cart.remove(ticket);
    }
    //Maksmise meetod.
    public boolean buy(Ticket ticket){

        double sum= ticket.getPrice();

        if(accountBalance >= sum){
            accountBalance -= sum;

            Owner thisOwner = ticket.getOwner();
            thisOwner.addToAccount(sum);
            thisOwner.addLog(getName()+" "+getFamilyName()+" ostis: " +ticket.getName()+ " hinna eest: "+sum);

            purchased.add(ticket); //FIXME: Ei tea veel kas läheb tarvis.
            addLog("Ostmine õnnestus: " +ticket.getName());

            return true;
        }else{
            //TODO: Teavitamine webappis.
            addLog("Ostmine ebaõnnestus: Kontol pole piisavalt raha!");
            return false;
        }
    }
    static int eventMinAge = 13;
    public boolean buyAll(){   //TODO!: Cartist ostmine, carti täishinna arvutamine.
        if (accountBalance >= cartPrice && !cart.isEmpty() && getAge() >= eventMinAge) {
            Owner thisOwner = cart.get(0).getOwner();
            accountBalance -= cartPrice;
            thisOwner.addToAccount(cartPrice);
            StringBuilder cartSum = new StringBuilder();
            for (int i = 0; i < cart.size(); i++) {
                cartSum.append(cart.get(i).getName()).append("\t");
                purchased.add(cart.get(i)); //FIXME: Ei tea veel kas läheb tarvis.
            }
            thisOwner.addLog(getName() + " " + getFamilyName() + " ostis: " + cartSum + " hinna eest: " + cartPrice);
            addLog("Ostmine õnnestus: " + cartSum);
            return true;
        } else {
            if(cart.isEmpty())
                addLog("Ostmine ebaõnnestus: Ostukäru on tühi!");
            else {
                addLog("Ostmine ebaõnnestus: Kontol pole piisavalt raha!");
            }
            return false;
        }
    }
}
