package com.ticket.ticketproject.controllers;

import com.ticket.ticketproject.actions.*;
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
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    OwnerService ownerService;


    @RequestMapping("/send")
    public ModelAndView sendEmailandSaveEntities(@SessionAttribute("client") Client client, @SessionAttribute("ticket")EventTicket ticket) throws Exception {
        client.setAccountBalance(1000);
        TicketCart ticketCart = new TicketCart(client,ownerService);
        boolean ost = ticketCart.buy(ticket);
        ModelAndView mav = new ModelAndView();
        if (ost) {
            long kood = ticket.getEventID() + ThreadLocalRandom.current().nextInt(0, 999999);
            String[] info = {String.valueOf(java.time.LocalDate.now()), "ID: " + ticket.getEventID(), "Nimi: " + eventService.getByID(ticket.getEventID()).getName(), "Piletitüüp: " + ticket.getName(), "Hind: " + ticket.getPrice()};
            String file = pilet.pdf(kood, info,client.getTicketCount());

            TicketHistory history = new TicketHistory(kood);
            ticketHistoryService.saveThis(history);


            mav.setViewName("redirect:confirmed");


            boolean toMail = client.isYes_mail();
            mav.addObject("toMail", toMail);
            if(toMail){
                emailKlass.email(client.getEmail(), file,true);


            }else{
            }

        }
        else{
            mav.setViewName("error123");
        }
        return mav;
    }


}