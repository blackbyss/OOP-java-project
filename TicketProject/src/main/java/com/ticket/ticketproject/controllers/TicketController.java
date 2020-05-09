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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
    @RequestMapping(value="/send", method = RequestMethod.GET)
    public ModelAndView sendEmailandSaveEntities(@SessionAttribute("client") Client client,@SessionAttribute("cart") TicketCart cart) throws Exception {
        client.setAccountBalance(1000);
        EventTicket ticket =cart.getCart().get(0);
        boolean ost = cart.buy(ticket,ownerService);
        ModelAndView mav = new ModelAndView();
        if (ost) {
            long kood = ticket.getEventID() + ThreadLocalRandom.current().nextInt(0, 999999);
            String[] info = {String.valueOf(java.time.LocalDate.now()), "ID: " + ticket.getEventID(), "Nimi: " + eventService.getByID(ticket.getEventID()).getName(), "Piletitüüp: " + ticket.getName(), "Hind: " + ticket.getPrice()};
            String file = pilet.pdf(kood, info,1); //TODO:!!!

            TicketHistory history = new TicketHistory(kood);


            if(clientService.getByNameAndFamiliyNameAndEmail(client.getName(),client.getFamilyName(),client.getEmail())==null){
                clientService.saveThis(client);
            }
            ticketHistoryService.saveThis(history);


            mav.setViewName("redirect:confirmed");


            boolean toMail = client.isYes_mail();
            mav.addObject("toMail", toMail);
            if(toMail){
                emailKlass.email(client.getEmail(), file);
            }

        }
        else{
            mav.setViewName("error");
        }
        return mav;
    }


    @RequestMapping(value="/cartview", method=RequestMethod.GET)
    public String cartView(@SessionAttribute("client")Client client, Model model, @SessionAttribute("cart")TicketCart cart){
        if(client.getName() != null && client.getFamilyName()!=null){
            model.addAttribute("ticketCart",cart.getCart());
            return "cart";
        }else{
            return "error";
        }
    }

    @RequestMapping(value="/cartview/remove/{ticketIndex}", method=RequestMethod.GET)
    public String removeTicket(@PathVariable("ticketIndex") String ticketIndex, @ModelAttribute("ticketCart")List<EventTicket>ticketCart,@SessionAttribute("client")Client client){
        try{
            int index = Integer.parseInt(ticketIndex);
            ticketCart.remove(index);
            return "redirect:cartview";
        }catch (NumberFormatException e){
            return "error";
        }
    }


}