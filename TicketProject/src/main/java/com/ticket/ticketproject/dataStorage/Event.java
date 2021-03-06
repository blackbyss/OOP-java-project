package com.ticket.ticketproject.dataStorage;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Event implements Storage {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String location;
    private LocalDate happeningTime;
    private long capacity; // mitu inimest mahub kokku
    private int ageLimit;
    private long ticketsLeft;
    private long owner_id;

    enum saveUserData {
        Yes,
        No
    }

    @Enumerated(EnumType.ORDINAL)
    saveUserData save_user_data;
    @Transient
    private Owner owner;


    public Event(String name, String location, int capacity/*, Date aeg */, long ticketsLeft) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        // this.aeg = aeg;
        this.ticketsLeft = ticketsLeft;
    }

    public Event() {

    }

    public long getId() {
        return id;
    }

    public long getTicketsLeft() {
        return ticketsLeft;
    }

    public void setTicketsLeft(long ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getHappeningTime() {
        return happeningTime;
    }

    public void setHappeningTime(LocalDate happeningTime) {
        this.happeningTime = happeningTime;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public saveUserData getSave_user_data() {
        return save_user_data;
    }

    public void setSave_user_data(saveUserData save_user_data) {
        this.save_user_data = save_user_data;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}




