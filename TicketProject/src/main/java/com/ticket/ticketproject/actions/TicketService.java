package com.ticket.ticketproject.actions;


import com.ticket.ticketproject.dataStorage.Client;
import com.ticket.ticketproject.dataStorage.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    TicketRepository repo;

    public void saveThis(Ticket ticket){
        repo.save(ticket);
    }

}
