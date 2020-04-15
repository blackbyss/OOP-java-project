package com.ticket.ticketproject.Classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

public class Person {


    private String name;
    private String familyName;
    private String gender;
    private int age;
    private String email;
    private long phone;
    private String address;
    private String county;
    private long index;
    private List<String> history;


    public Person(String name, String familyName, String gender, int age, String email, long phone, String address, String county, long index) {
        this.name = name;
        this.familyName = familyName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.county = county;
        this.index = index;
        history = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public long getPhone() {
        return phone;
    }

    public List<String> getHistory() {
        return history;
    }
    public int getAge(){return age;}

    public void addLog(String newLog) {
        history.add(newLog);


    }
}

