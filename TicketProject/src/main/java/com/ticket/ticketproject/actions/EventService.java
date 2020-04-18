package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.Event;
import com.ticket.ticketproject.dataStorage.EventTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepo;

    public Event getByID(long id){
        Optional<Event> optional=  eventRepo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }
    }
}
