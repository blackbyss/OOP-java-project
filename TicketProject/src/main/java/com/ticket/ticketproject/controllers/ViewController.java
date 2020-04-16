package com.ticket.ticketproject.controllers;


        import com.ticket.ticketproject.actions.ClientService;
        import com.ticket.ticketproject.dataStorage.Client;
        import com.ticket.ticketproject.dataStorage.FormData;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;


        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpSession;
        import java.util.Date;
        import java.util.concurrent.atomic.AtomicInteger;

@Controller
@SessionAttributes("client")
public class ViewController {

    @Autowired
    ClientService service;

@ModelAttribute("client")

public Client createClient(){
    return new Client();
}

//Index leht
    @RequestMapping("/")
            public String index(){
        return "index";
    }

    AtomicInteger counter = new AtomicInteger(0);

//Form
    @RequestMapping(value="/client-form/{eventID}", method = RequestMethod.GET)
    public String LoadForm(Model model, HttpSession session, @PathVariable String eventID) {
        model.addAttribute("form", new FormData());
        model.addAttribute("datetime", new Date());
        model.addAttribute("counter",counter.incrementAndGet());
        return "client-form";
    }

    //Kinnitus- edastab Client isendi TicketControllerile.
    @RequestMapping(value="/confirmed", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("client") Client client,@ModelAttribute("form") FormData form, HttpServletRequest req) {
        form.setUser_type("client");
        client = new Client(form.getName(),form.getFamilyName(),"mees",Integer.parseInt(form.getAge()),form.getEmail(),form.getIban(),form.getAddress(),form.getCounty(),Long.parseLong(form.getIndex()),form.isYes_mail(),1000);
        service.saveThis(client);
        return "confirmation";
    }


}
