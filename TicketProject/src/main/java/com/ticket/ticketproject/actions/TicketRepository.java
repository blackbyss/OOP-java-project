package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.EventTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TicketRepository extends CrudRepository<EventTicket, Long> {
    EventTicket findByTicketTypeAndEventID(long a, long b);

    ArrayList<EventTicket> findAllByEventID(long id);


}
