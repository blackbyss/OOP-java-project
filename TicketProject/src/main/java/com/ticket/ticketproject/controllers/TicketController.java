package com.ticket.ticketproject.controllers;



import com.ticket.ticketproject.actions.TicketService;
import com.ticket.ticketproject.dataStorage.Event;
import com.ticket.ticketproject.dataStorage.EventTicket;
import com.ticket.ticketproject.functionalities.PdfPilet;
import com.ticket.ticketproject.functionalities.Email;
import com.ticket.ticketproject.dataStorage.TestTicket;

import com.ticket.ticketproject.actions.ClientRepository;
import com.ticket.ticketproject.dataStorage.Client;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;


import java.util.concurrent.ThreadLocalRandom;

@Controller
public class TicketController {
    PdfPilet pilet = new PdfPilet();
    Email emailKlass = new Email();
    @Autowired
    TicketService ticketService;

@RequestMapping("/send")
public String saveClient(@SessionAttribute("client") Client client, @SessionAttribute("ticket")EventTicket ticket) throws Exception {
    long kood = ticket.getEventID() + ThreadLocalRandom.current().nextInt(0, 999999);
    // Event uusEvent = ticket.getEvent();
    String[] info = {String.valueOf(java.time.LocalDate.now()), "ID: "+ ticket.getEventID(), "Nimi: (event nimi)", "Piletitüüp: "+ticket.getName(), "Hind: "+ ticket.getPrice()};
    String file = pilet.pdf(kood, info);
    emailKlass.email(client.getEmail(), file);
    // ticketService.saveThis(ticket);
    return "redirect:confirmed";
}
}