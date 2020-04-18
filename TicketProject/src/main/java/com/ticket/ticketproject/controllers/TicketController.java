package com.ticket.ticketproject.controllers;



import com.ticket.ticketproject.actions.TicketService;
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
    @Autowired
    TicketService ticketService;

@GetMapping("/info")
public String saveClient(@SessionAttribute("client") Client client, @SessionAttribute("ticket")EventTicket ticket) throws Exception {
    long kood = ticket.getEventID() + ThreadLocalRandom.current().nextInt(0, 999999);
    // Event uusEvent = ticket.getEvent();
    String[] info = {String.valueOf(java.time.LocalDate.now()), "ID: "+ ticket.getEventID(), "Nimi: (event nimi)", "Piletitüüp: "+ticket.getName(), "Hind: "+ ticket.getPrice()};
    String file = pilet.pdf(kood, info);
    emailKlass.email(client.getEmail(), file);
    // ticketService.saveThis(ticket);
    return client.clientToString();
}
}
