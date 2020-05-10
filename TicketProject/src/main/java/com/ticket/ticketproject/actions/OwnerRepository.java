package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByEventID(long eventID);
}