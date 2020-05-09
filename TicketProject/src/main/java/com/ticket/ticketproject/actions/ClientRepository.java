
package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.Client;
import com.ticket.ticketproject.dataStorage.FormData;
import com.ticket.ticketproject.dataStorage.Storage;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    public Client findClientByNameAndFamilyNameAndEmail(String name, String familyName,String email);
}