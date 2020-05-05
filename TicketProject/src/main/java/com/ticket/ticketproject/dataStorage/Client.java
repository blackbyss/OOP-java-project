package com.ticket.ticketproject.dataStorage;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends Person implements Storage {
    private boolean yes_mail; //Kas soovib emailiga piletit?
    @NotNull
    private double accountBalance; //Konto
    @Transient
    private List<Ticket> cart; //Ostukorv
    @Transient
    private List<Ticket>purchased; //FIXME: Ei tea veel kas läheb tarvis.
    @Transient
    private double cartPrice;

    public Client(String eesnimi, String perenimi, int vanus, String email, String iban, String aadress, String maakond, long indeks, boolean yes_mail,double accountBalance) {
        super(eesnimi, perenimi, vanus, email, iban, aadress, maakond, indeks);
        this.yes_mail = yes_mail;
        this.accountBalance=accountBalance;
        cart=new ArrayList<Ticket>();
        purchased=new ArrayList<Ticket>();
    }
    public Client(){
    }

    public boolean isYes_mail() {
        return yes_mail;
    }

    public void setYes_mail(boolean yes_mail) {
        this.yes_mail = yes_mail;
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

            Owner thisOwner = ticket.getOwner(); //FIXME: Lihtsalt ajutine asendus, et H2 tabelid luua.
            thisOwner.addToAccount(sum);
            thisOwner.addLog(getName()+" "+getFamilyName()+" ostis: " +ticket.getName()+ " hinna eest: "+sum);

            purchased.add(ticket);
            addLog("Ostmine õnnestus: " +ticket.getName());

            return true;
        }else{
            //TODO: Teavitamine webappis.
            addLog("Ostmine ebaõnnestus: Kontol pole piisavalt raha!");
            return false;
        }
    }

    public boolean buyAll(){
        if (accountBalance >= cartPrice && !cart.isEmpty()) {
            Owner thisOwner = cart.get(0).getOwner();
            accountBalance -= cartPrice;
            thisOwner.addToAccount(cartPrice);
            StringBuilder cartSum = new StringBuilder();
            for (int i = 0; i < cart.size(); i++) {
                cartSum.append(cart.get(i).getName()).append("\t");
                purchased.add(cart.get(i));
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

    public String clientToString(){
       String s= this.toString();

       return s+" "+ yes_mail;
    }
}
