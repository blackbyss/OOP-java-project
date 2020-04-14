package com.ticket.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ticket.controllers", "com.ticket.dataStorage","com.ticket.functionalities"})
@ComponentScan(basePackages = {"com.ticket.controllers", "com.ticket.dataStorage","com.ticket.functionalities"})
public class TicketprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketprojectApplication.class, args);
	}

}
