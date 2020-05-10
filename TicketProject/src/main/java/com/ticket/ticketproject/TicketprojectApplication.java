package com.ticket.ticketproject;

import com.ticket.ticketproject.actions.ClientRepository;
import com.ticket.ticketproject.dataStorage.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class TicketprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketprojectApplication.class, args);
    }


}
