package com.ticket.ticketproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootTest
@EnableJpaRepositories(basePackages = {"com.ticket.ticketproject.controllers", "com.ticket.ticketproject.dataStorage", "com.ticket.ticketproject.functionalities", "com.ticket.ticketproject.actions"})
@ComponentScan(basePackages = {"com.ticket.ticketproject.controllers", "com.ticket.ticketproject.dataStorage", "com.ticket.ticketproject.functionalities", "com.ticket.ticketproject.actions"})
class TicketprojectApplicationTests {

	@Test
	void contextLoads() {
	}

}
