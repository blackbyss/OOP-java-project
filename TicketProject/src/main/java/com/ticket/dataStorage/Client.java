package com.ticket.dataStorage;


import java.util.ArrayList;
import java.util.List;

    public class Client extends Person {
        boolean yes_mail; //Kas soovib emailiga piletit?
        private double accountBalance; //Konto
        private List<Ticket> cart; //Ostukorv
        private double cartPrice;
        private List<Ticket> purchased; //FIXME: Ei tea veel kas läheb tarvis.


        public void addToList(Ticket ticket) {
            cartPrice += ticket.getPrice();
            cart.add(ticket);
        }

        public void removeFromList(Ticket ticket) {
            cartPrice -= ticket.getPrice();
            cart.remove(ticket);
        }
        public void clearList(Ticket ticket){
            cartPrice = 0;
            cart.clear();
        }

        
        //Maksmise meetod.
        public boolean buy(Ticket ticket) {

            double sum = ticket.getPrice();

            if (accountBalance >= sum) {
                accountBalance -= sum;

                Owner thisOwner = ticket.getOwner(); //FIXME: Lihtsalt ajutine asendus, et H2 tabelid luua.
                thisOwner.addToAccount(sum);
                thisOwner.addLog(getName() + " " + getFamilyName() + " ostis: " + ticket.getName() + " hinna eest: " + sum);

                purchased.add(ticket);
                addLog("Ostmine õnnestus: " + ticket.getName());

                return true;
            } else {
                //TODO: Teavitamine webappis.
                addLog("Ostmine ebaõnnestus: Kontol pole piisavalt raha!");
                return false;
            }
        }

        public boolean buyAll() {
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
                if (cart.isEmpty())
                    addLog("Ostmine ebaõnnestus: Ostukäru on tühi!");
                else {
                    addLog("Ostmine ebaõnnestus: Kontol pole piisavalt raha!");
                }
                return false;
            }
        }
    }

