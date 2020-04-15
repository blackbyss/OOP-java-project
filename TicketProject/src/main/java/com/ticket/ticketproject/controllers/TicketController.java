package com.ticket.ticketproject.controllers;





import com.ticket.ticketproject.dataStorage.Client;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/client")
public class TicketController {




@GetMapping("/info")
public String clientInfo(@SessionAttribute("client") Client client) {


    return client.clientToString();
}

}
