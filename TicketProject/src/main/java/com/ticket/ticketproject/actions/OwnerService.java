package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    @Autowired
    OwnerRepository ownerRepo;

    public Owner getByEventID(Long event_id) {
        return ownerRepo.findByEventID(event_id);
    }

}