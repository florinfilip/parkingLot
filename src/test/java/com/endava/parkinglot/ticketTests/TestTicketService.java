package com.endava.parkinglot.ticketTests;

import com.endava.parkinglot.repositories.TicketRepository;
import com.endava.parkinglot.service.Impl.TicketServiceImpl;
import com.endava.parkinglot.service.TicketService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestTicketService {

    @Mock
    private TicketRepository ticketRepository;
    private AutoCloseable autoCloseable;
    private TicketService testTicketService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        testTicketService = new TicketServiceImpl(ticketRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void canGetTickets(){

        System.out.println(testTicketService.getParkingTickets());
        Mockito.verify(ticketRepository).findAll();

    }
}
