package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
