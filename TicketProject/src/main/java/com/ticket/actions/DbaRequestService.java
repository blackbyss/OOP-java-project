package com.ticket.actions;

import com.ticket.dataStorage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
@Transactional
@Service
public class DbaRequestService {
    @Autowired
    private DbaRequestHandler handler;

    public void create(Storage storage){
        handler.create(storage);
    }
}
*/