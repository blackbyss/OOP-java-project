package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketHistoryService {
    @Autowired TicketHistoryRepository repo;

    public void saveThis(TicketHistory ticket_history){
        repo.save(ticket_history);
    }


}
