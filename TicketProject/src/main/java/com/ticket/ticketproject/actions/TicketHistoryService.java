package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.Client;
import com.ticket.ticketproject.dataStorage.EventTicket;
import com.ticket.ticketproject.dataStorage.FormData;
import com.ticket.ticketproject.dataStorage.TicketHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketHistoryService {
    @Autowired TicketHistoryRepository repo;

    public void saveThis(TicketHistory ticket_history){
        repo.save(ticket_history);
    }

}
