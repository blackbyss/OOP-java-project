
package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findClientByNameAndFamilyNameAndEmail(String name, String familyName, String email);
}