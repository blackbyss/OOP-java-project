package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepo;

    public Event getByID(long id) {
        Optional<Event> optional = eventRepo.findById(id);
        return Optional.of(optional.get()).orElse(null);
    }

    public List<Event> getAll() {
        List<Event> eventList = new ArrayList<>();
        eventRepo.findAll().forEach(eventList::add);
        return eventList;
    }

    public void saveThis(Event event) {
        eventRepo.save(event);
    }
}
