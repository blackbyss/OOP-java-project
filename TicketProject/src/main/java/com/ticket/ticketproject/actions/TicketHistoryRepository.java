package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketHistoryRepository extends CrudRepository<TicketHistory, Long> {

}