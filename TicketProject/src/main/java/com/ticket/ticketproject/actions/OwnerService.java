package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.Event;
import com.ticket.ticketproject.dataStorage.EventTicket;
import com.ticket.ticketproject.dataStorage.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    OwnerRepository ownerRepo;

    public Owner getByEventID(Long event_id) {
        return ownerRepo.findByEventID(event_id);
    }

}