package com.ticket.ticketproject.dataStorage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class exampleEntity {

    @Id
    @GeneratedValue
    long id;
    String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
