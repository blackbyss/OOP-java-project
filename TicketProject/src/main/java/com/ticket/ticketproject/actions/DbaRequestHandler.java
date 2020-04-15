package com.ticket.ticketproject.actions;


import com.ticket.ticketproject.dataStorage.Storage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class DbaRequestHandler {

    private EntityManager em;

    //Andmebaasi lisamine
    public void create(Storage storage){
        em.persist(storage);
    }


    //Andbebaasi elemendi uuendamine
    public void update(Storage storage) {
        em.merge(storage);
    }


    //Andmebaasist isendi leidmine
    public Storage getInstanceById(Class<Storage> c,long id) {
        return em.find(c,id);
    }

    //Andmebaasist isendi kustutamine
    public void delete(Class<Storage> c,long id){
       Storage storage= getInstanceById(c,id);
       if(storage != null){
           em.remove(storage);
       }
    }


}
