package com.ticket.ticketproject.actions;


import com.ticket.ticketproject.dataStorage.EventTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    TicketRepository repo;

    public void saveThis(EventTicket ticket){
        repo.save(ticket);
    }

    public EventTicket getByID(long id){
        Optional<EventTicket> optional=  repo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }
    }

    public EventTicket getByEventIdAndTicketType(long eventID,long ticketType){
       return repo.findByTicketTypeAndEventID(ticketType,eventID);
    }

}
