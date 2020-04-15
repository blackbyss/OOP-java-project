package com.ticket.ticketproject.controllers;





import com.ticket.ticketproject.actions.ClientRepository;
import com.ticket.ticketproject.dataStorage.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;

@RestController
@EnableAutoConfiguration
@RequestMapping("/client")
public class TicketController {

@GetMapping("/info")
public String saveClient(@SessionAttribute("client") Client client) {
    return client.clientToString();
}

}
