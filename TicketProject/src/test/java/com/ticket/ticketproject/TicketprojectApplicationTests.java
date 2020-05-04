package com.ticket.ticketproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootTest
@EnableJpaRepositories(basePackages = {"com.ticket.controllers", "com.ticket.dataStorage", "com.ticket.ticketproject.functionalities"})
@ComponentScan(basePackages = {"com.ticket.controllers", "com.ticket.dataStorage", "com.ticket.ticketproject.functionalities"})
class TicketprojectApplicationTests {

	@Test
	void contextLoads() {
	}

}
