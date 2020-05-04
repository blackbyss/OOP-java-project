package com.ticket.ticketproject.actions;

import com.ticket.ticketproject.dataStorage.Client;
import com.ticket.ticketproject.dataStorage.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    ClientRepository repo;

    public void saveThis(Client client) {
        repo.save(client);
    }
}
