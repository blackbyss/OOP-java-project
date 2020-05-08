package com.ticket.ticketproject.controllers;


        import com.ticket.ticketproject.actions.ClientService;
        import com.ticket.ticketproject.actions.EventService;
        import com.ticket.ticketproject.actions.TicketService;
        import com.ticket.ticketproject.dataStorage.Client;
        import com.ticket.ticketproject.dataStorage.Event;
        import com.ticket.ticketproject.dataStorage.EventTicket;
        import com.ticket.ticketproject.dataStorage.FormData;
        import org.hibernate.Session;
        import org.hibernate.query.Query;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.core.io.InputStreamResource;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.ModelAndView;


        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.util.Date;
        import java.util.Iterator;
        import java.util.List;
        import java.util.concurrent.atomic.AtomicInteger;

@Controller
@SessionAttributes({"client","ticket"})
public class ViewController {

    //Päringute service-id
    @Autowired
    ClientService clientService;

    @Autowired
    TicketService ticketService;

    @Autowired
    EventService eventService;


//isendite loomine sessionisse panemiseks
@ModelAttribute("client")
public Client createClient(){
    return new Client();
}

@ModelAttribute("ticket")
public EventTicket createEventTicket(){return new EventTicket();}

//Index leht ja pileti valimine
    @RequestMapping("/")
    public String index(Model model){
        List<Event> events = eventService.getAll();
        model.addAttribute("eventList",events);
    return "index";
    }


//Pileti tüübi valimine
    @RequestMapping("/selection/{eventID}")
     public String selectTicket(Model model,@PathVariable String eventID){
    //Päring vastava evendi kõikide valikus olevate piletite saamiseks.
    List<EventTicket> ticketList = ticketService.getAllByEventId(Integer.parseInt(eventID));
    model.addAttribute("ticketList",ticketList);

    return "select-ticket";
    }

    //Testimiseks atomic counter.
    AtomicInteger counter = new AtomicInteger(0);

//Form
    @RequestMapping(value="client-form/{eventID}/{ticketType}", method = RequestMethod.GET)
    public String LoadForm(Model model, @PathVariable String eventID, @PathVariable String ticketType, @ModelAttribute EventTicket ticket) {

        //EventTicketi päring ürituse id ja piletitüübi abil
        int event = Integer.parseInt(eventID);
        int type= Integer.parseInt(ticketType);
      ticket = ticketService.getByEventIdAndTicketType(event,type);
      //Model andmete formi abil täitmiseks ja edastamiseks.
        model.addAttribute("ticket",ticket);
        model.addAttribute("form", new FormData());
        model.addAttribute("datetime", new Date());
        model.addAttribute("counter",counter.incrementAndGet());
        return "client-form";
    }
    //redirect leht
    //edastab Client ja EventTicket isendid TicketControllerile.
    @RequestMapping(value="/calculate", method = RequestMethod.POST)
    public String submitForm(Model model,@ModelAttribute("client") Client client, @ModelAttribute("form") FormData form,@SessionAttribute EventTicket ticket) {
        form.setUser_type("client");
        client = new Client(form.getName(),form.getFamilyName(),Integer.parseInt(form.getAge()),form.getEmail(),form.getIban(),form.getAddress(),form.getCounty(),Long.parseLong(form.getIndex()),form.isYes_mail(),1000);
        //Client isend salvestatakse andmebaasi.
        clientService.saveThis(client);
        return "redirect:send";
    }
    //Viimane kinnitusleht
    @RequestMapping(value="/confirmed")
    public String confirm(@ModelAttribute("client") Client client, @ModelAttribute("toMail") boolean toMail){
        return "confirmation";
    }

    @RequestMapping(value = "confirmed/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object>  downloadFile() throws IOException
    {
        String filename = "DownloadTestimiseks.pdf";
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);


        return responseEntity;
    }

}
