package com.ticket.ticketproject.actions;


import com.ticket.ticketproject.dataStorage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class DbaRequestService {
    
    private DbaRequestHandler handler;

    public void create(Storage storage){
        handler.create(storage);
    }
}
