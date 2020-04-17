package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.EventTicket;
import com.ticket.ticketproject.dataStorage.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface TicketRepository extends CrudRepository<EventTicket,Long> {
   public EventTicket findByTicketTypeAndEventID(long a, long b);

   public ArrayList<EventTicket> findAllByEventID(long id);
}
