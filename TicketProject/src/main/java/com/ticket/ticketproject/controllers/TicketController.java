package com.ticket.ticketproject.controllers;



import com.ticket.ticketproject.dataStorage.Event;
import com.ticket.ticketproject.dataStorage.EventTicket;
import com.ticket.ticketproject.functionalities.PdfPilet;
import com.ticket.ticketproject.functionalities.Email;
import com.ticket.ticketproject.dataStorage.TestTicket;

import com.ticket.ticketproject.actions.ClientRepository;
import com.ticket.ticketproject.dataStorage.Client;
import com.ticket.ticketproject.dataStorage.Ticket;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@EnableAutoConfiguration
@RequestMapping("/client")
public class TicketController {
    PdfPilet pilet = new PdfPilet();
    Email emailKlass = new Email();
    TestTicket ticket = new TestTicket(123, "Testimispilet", "Standard", 12.34);

@GetMapping("/info")
public String saveClient(@SessionAttribute("client") Client client) throws Exception {
    Long kood = Long.parseLong(client.getIban()) + ThreadLocalRandom.current().nextInt(0, 999999);
    String[] info = {"Kuupäev: 17.04.2020", "ID: "+ ticket.getId(), "Nimi: "+ticket.getName(), "Piletitüüp: "+ticket.getType(), "Hind: "+ ticket.getPrice()};
    String file = pilet.pdf(kood, info);
    emailKlass.email(client.getEmail(), file);
    return client.clientToString();
}
}
