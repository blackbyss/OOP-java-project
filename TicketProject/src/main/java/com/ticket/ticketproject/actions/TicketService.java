package com.ticket.ticketproject.actions;


import com.ticket.ticketproject.dataStorage.EventTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return Optional.of(optional.get()).orElse(null);
    }

    public EventTicket getByEventIdAndTicketType(long eventID,long ticketType){
       return repo.findByTicketTypeAndEventID(ticketType,eventID);
    }

    public List<EventTicket> getAllByEventId (long id){
        return repo.findAllByEventID(id);
    }

}
