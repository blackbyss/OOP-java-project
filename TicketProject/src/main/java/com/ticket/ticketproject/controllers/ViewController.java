package com.ticket.ticketproject.controllers;


        import com.ticket.ticketproject.actions.ClientService;
        import com.ticket.ticketproject.actions.TicketService;
        import com.ticket.ticketproject.dataStorage.Client;
        import com.ticket.ticketproject.dataStorage.Event;
        import com.ticket.ticketproject.dataStorage.EventTicket;
        import com.ticket.ticketproject.dataStorage.FormData;
        import org.hibernate.Session;
        import org.hibernate.query.Query;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;


        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpSession;
        import java.util.Date;
        import java.util.List;
        import java.util.concurrent.atomic.AtomicInteger;

@Controller
@SessionAttributes({"client","ticket"})
public class ViewController {

    @Autowired
    ClientService clientService;

    @Autowired
    TicketService ticketService;

@ModelAttribute("client")

public Client createClient(){
    return new Client();
}

@ModelAttribute("ticket")
public EventTicket createEventTicket(){return new EventTicket();}

//Index leht
    @RequestMapping("/")
    public String index(){
    return "index";
    }



    @RequestMapping("/selection/{eventID}")
     public String selectTicket(Model model,@PathVariable String eventID){
    List<EventTicket> ticketList = ticketService.getAllByEventId(Integer.parseInt(eventID));
    model.addAttribute("ticketList",ticketList);

    return "select-ticket";
    }

    AtomicInteger counter = new AtomicInteger(0);

//Form
    @RequestMapping(value="client-form/{eventID}/{ticketType}", method = RequestMethod.GET)
    public String LoadForm(Model model, @PathVariable String eventID, @PathVariable String ticketType, @ModelAttribute EventTicket ticket) {
        int event = Integer.parseInt(eventID);
        int type= Integer.parseInt(ticketType);

      ticket = ticketService.getByEventIdAndTicketType(event,type);
        model.addAttribute("ticket",ticket);
        model.addAttribute("form", new FormData());
        model.addAttribute("datetime", new Date());
        model.addAttribute("counter",counter.incrementAndGet());
        return "client-form";
    }

    //Kinnitus- edastab Client isendi TicketControllerile.
    @RequestMapping(value="/calculate", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("client") Client client, @ModelAttribute("form") FormData form,@SessionAttribute EventTicket ticket) {
        form.setUser_type("client");
        client = new Client(form.getName(),form.getFamilyName(),Integer.parseInt(form.getAge()),form.getEmail(),form.getIban(),form.getAddress(),form.getCounty(),Long.parseLong(form.getIndex()),form.isYes_mail(),1000);
        clientService.saveThis(client);
        return "redirect:send";
    }
    @RequestMapping(value="/confirmed")
    public String confirm(@ModelAttribute("client") Client client){
        return "confirmation";
    }

}
