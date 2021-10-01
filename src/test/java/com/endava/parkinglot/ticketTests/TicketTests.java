package com.endava.parkinglot.ticketTests;

import com.endava.parkinglot.parking.ticket.ParkingTicket;
import com.endava.parkinglot.repositories.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TicketTests {
    @Autowired
    private TicketRepository testTicketRepository;

    @Test
    void itShouldReturnTicketList(){
      ParkingTicket parkingTicket= testTicketRepository.findAll().stream().findFirst().get();

       log.info(parkingTicket.toString());
    }
}
