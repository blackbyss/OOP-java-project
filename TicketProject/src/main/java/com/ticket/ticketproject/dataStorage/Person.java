package com.ticket.ticketproject.dataStorage;


import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public class Person implements Storage {
    public Person() {
    }

    public Person(String name, String familyName, int age, String email, String iban, String address, String county, long index, double accountBalance) {
        this.name = name;
        this.familyName = familyName;
        this.age = age;
        this.email = email;
        this.iban = iban;
        this.address = address;
        this.county = county;
        this.index = index;
        this.accountBalance = accountBalance;
    }

    //Variables
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String familyName;
    private int age;
    private String email;
    private String iban;
    private String address;
    private String county;
    private long index;
    private int ticketCount;
    @NotNull
    private double accountBalance;


    //Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", iban='" + iban + '\'' +
                ", address='" + address + '\'' +
                ", county='" + county + '\'' +
                ", index=" + index +
                '}';
    }
}