package com.ticket.ticketproject.controllers;


        import com.ticket.ticketproject.actions.ClientService;
        import com.ticket.ticketproject.actions.EventService;
        import com.ticket.ticketproject.actions.TicketCart;
        import com.ticket.ticketproject.actions.TicketService;
        import com.ticket.ticketproject.dataStorage.Client;
        import com.ticket.ticketproject.dataStorage.Event;
        import com.ticket.ticketproject.dataStorage.EventTicket;
        import com.ticket.ticketproject.dataStorage.FormData;
        import com.ticket.ticketproject.functionalities.ZipDirectory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.core.io.InputStreamResource;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindException;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.client.HttpClientErrorException;
        import org.springframework.web.servlet.ModelAndView;


        import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.util.Date;
        import java.util.List;
        import java.util.concurrent.atomic.AtomicInteger;

@Controller
@SessionAttributes({"client","cart","ticket"})
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
    public Client createClient() {
        return new Client();
    }


    @ModelAttribute("cart")
    public TicketCart createCart() {
        return new TicketCart();
    }

    @ModelAttribute("ticket")
    public EventTicket createTicket() {
        return new EventTicket();
    }

    @RequestMapping("/")
    public String index() {
        deleteTickets();
        return "index";
    }

    @RequestMapping(value = "/client-form")
    public String getFormData(Model model) {
        model.addAttribute("form", new FormData());
        return "client-form";
    }


    //Index leht ja pileti valimine
    @RequestMapping("/selection")
    public String selectEvent(Model model,@ModelAttribute("client") Client client, @ModelAttribute("form") FormData form) {

            if(client.getName()==null){
                client = new Client(form.getName(), form.getFamilyName(), Integer.parseInt(form.getAge()), form.getEmail(), form.getIban(), form.getAddress(), form.getCounty(), Long.parseLong(form.getIndex()), form.isYes_mail(), 1000);

            }
            List<Event> events = eventService.getAll();

            model.addAttribute("eventList", events);
            model.addAttribute("vanus",client.getAge());


            return "select-event";

    }



    //Pileti tüübi valimine
    @RequestMapping("/selection/{eventID}")
    public String selectTicket(Model model, @PathVariable String eventID) {
        //Päring vastava evendi kõikide valikus olevate piletite saamiseks.
        List<EventTicket> ticketList = ticketService.getAllByEventId(Integer.parseInt(eventID));
        model.addAttribute("ticketList", ticketList);

        return "select-ticket";
    }

    //Testimiseks atomic counter.
    AtomicInteger counter = new AtomicInteger(0);

    //Form
    @RequestMapping(value = "selection/{eventID}/{ticketType}", method = RequestMethod.GET)
    public ModelAndView LoadForm(Model model, @PathVariable String eventID, @PathVariable String ticketType, @ModelAttribute("ticket") EventTicket ticket, @ModelAttribute("cart") TicketCart cart, @ModelAttribute("client")Client client) {
        ModelAndView mav = new ModelAndView();
        //EventTicketi päring ürituse id ja piletitüübi abil
        try{

            int event = Integer.parseInt(eventID);
            int type = Integer.parseInt(ticketType);
            ticket = ticketService.getByEventIdAndTicketType(event, type);
            //Model andmete formi abil täitmiseks ja edastamiseks.

            cart.setClient(client);
            cart.addToCart(ticket);
            mav.setViewName("redirect:/cart");
            return mav;
        }catch (NumberFormatException e){
            mav.setViewName("error");
            return mav;
        }

    }
    @GetMapping("/cart")
    public String showCart(@SessionAttribute("cart") TicketCart cart, Model model){
        model.addAttribute("ticketCart",cart.getCart());
        return "cart";
    }


    @RequestMapping(value = "cart/remove/{ticketIndex}", method = RequestMethod.GET)
    public String removeTicket(@PathVariable("ticketIndex") String ticketIndex, @SessionAttribute("cart") TicketCart cart) {
            int index = Integer.parseInt(ticketIndex);
            cart.removeByIndex(index);
            return "redirect:/cart";

    }


    //Viimane kinnitusleht
    @RequestMapping(value = "/confirmed")
    public String confirm(@ModelAttribute("client") Client client, @ModelAttribute("toMail") boolean toMail,  @SessionAttribute("cart") TicketCart cart) {
        cart.clearCart();
        return "confirmation";
    }

    @RequestMapping(value = "confirmed/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> downloadFile() throws IOException {
        File folder = new File(System.getProperty("user.dir") + "\\piletid");
        File[] listOfFiles = folder.listFiles();
        File file;
        InputStreamResource resource;
        if(listOfFiles.length == 1) {
            file = new File(folder + "\\" + listOfFiles[0].getName());
            resource = new InputStreamResource(new FileInputStream(file));
        }
        else {
            ZipDirectory zipDirectory = new ZipDirectory();
            zipDirectory.generateFileList(new File(System.getProperty("user.dir")+"\\piletid"));
            zipDirectory.zipIt(System.getProperty("user.dir")+"\\piletid.zip");
            file = new File(System.getProperty("user.dir")+"\\piletid.zip");
            resource = new InputStreamResource(new FileInputStream(file));

        }


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

    private void deleteTickets(){
        File tickets = new File(System.getProperty("user.dir")+"\\piletid");
        boolean exists = tickets.exists();
        if(exists){
            String[]entries = tickets.list();
            for(String s: entries){
                File currentFile = new File(tickets.getPath(),s);
                currentFile.delete();
            }
        }
        else{
            String path = System.getProperty("user.dir")+"\\piletid";
            File directory = new File(path);
            directory.mkdir();
        }
    }
}