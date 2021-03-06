package com.ticket.ticketproject.controllers;

import com.ticket.ticketproject.actions.*;
import com.ticket.ticketproject.dataStorage.Client;
import com.ticket.ticketproject.dataStorage.Event;
import com.ticket.ticketproject.dataStorage.EventTicket;
import com.ticket.ticketproject.dataStorage.TicketHistory;
import com.ticket.ticketproject.functionalities.Email;
import com.ticket.ticketproject.functionalities.PdfPilet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
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
    @Autowired
    ClientService clientService;

    @Transactional
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ModelAndView sendEmailandSaveEntities(@SessionAttribute("client") Client client, @SessionAttribute("cart") TicketCart cart) throws Exception {
        boolean ost = cart.buyAll(ownerService);
        ModelAndView mav = new ModelAndView();
        if (ost) {
            for (int i = 0; i < cart.getCart().size(); i++) {

                EventTicket ticket = cart.getCart().get(i);
                long kood = ticket.getEventID() + ThreadLocalRandom.current().nextInt(0, 999999);
                Event event =eventService.getByID(ticket.getEventID());
                if (event.getTicketsLeft() > 0) {
                    event.setTicketsLeft(event.getTicketsLeft() - 1);
                    eventService.saveThis(event);
                }
                String[] info = {String.valueOf(java.time.LocalDate.now()), "ID: " + ticket.getEventID(), "Nimi: " + event.getName(), "Piletitüüp: " + ticket.getName(), "Hind: " + ticket.getPrice()};
                pilet.pdf(kood, info);

                TicketHistory history = new TicketHistory(kood);


                if (clientService.getByNameAndFamiliyNameAndEmail(client.getName(), client.getFamilyName(), client.getEmail()) == null) {
                    clientService.saveThis(client);
                }
                ticketHistoryService.saveThis(history);


                mav.setViewName("redirect:confirmed");


                boolean toMail = client.isYes_mail();
                mav.addObject("toMail", toMail);
                if (toMail) {
                    emailKlass.email(client.getEmail());
                }
            }
        } else {
            mav.setViewName("redirect:/cart");
            String message="Teil pole kontol piisavalt raha!";
            mav.addObject("message",message);
        }
        return mav;
    }


}