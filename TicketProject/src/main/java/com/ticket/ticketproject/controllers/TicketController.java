package com.ticket.ticketproject.controllers;

import com.ticket.ticketproject.actions.EventService;
import com.ticket.ticketproject.actions.TicketHistoryService;
import com.ticket.ticketproject.actions.TicketService;
import com.ticket.ticketproject.dataStorage.Event;
import com.ticket.ticketproject.dataStorage.EventTicket;
import com.ticket.ticketproject.dataStorage.TicketHistory;
import com.ticket.ticketproject.functionalities.PdfPilet;
import com.ticket.ticketproject.functionalities.Email;
import com.ticket.ticketproject.dataStorage.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@EnableAutoConfiguration
public class TicketController {
    PdfPilet pilet = new PdfPilet();
    Email emailKlass = new Email();
    @Autowired
    EventService eventService;
    @Autowired
    TicketService ticketService;
    @Autowired
    TicketHistoryService ticketHistoryService;

@RequestMapping("/send")
public String saveClient(@SessionAttribute("client") Client client, @SessionAttribute("ticket")EventTicket ticket) throws Exception {
    long kood = ticket.getEventID() + ThreadLocalRandom.current().nextInt(0, 999999);
    TicketHistory history = new TicketHistory(kood,client.getName(), client.getFamilyName(), ticket.getPrice());
    Event uusEvent = eventService.getByID(ticket.getEventID());
    String[] info = {String.valueOf(java.time.LocalDate.now()), "ID: "+ ticket.getEventID(), "Nimi: "+uusEvent.getName(), "Piletitüüp: "+ticket.getName(), "Hind: "+ ticket.getPrice()};
    String file = pilet.pdf(kood, info);
    emailKlass.email(client.getEmail(), file);
    // ticketService.saveThis(ticket);
    ticketHistoryService.saveThis(history);
    return "redirect:confirmed";
}
}
