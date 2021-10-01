package com.endava.parkinglot.controllers;


import com.endava.parkinglot.parking.ticket.ParkingTicket;
import com.endava.parkinglot.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("tickets")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping()
    public ResponseEntity getTickets(){
        return new ResponseEntity(ticketService.getParkingTickets(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity removeTicket(@PathVariable int id){
        ticketService.deleteParkingTicket(id);
       return new ResponseEntity(HttpStatus.GONE);
    }

    @PostMapping("/new")
    public ResponseEntity createTicket(@RequestBody ParkingTicket parkingTicket){
        ticketService.createParkingTicketWithoutReservation(parkingTicket);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity updateTicket(@RequestBody ParkingTicket parkingTicket){
        ticketService.updateParkingTicket(parkingTicket);
       return new ResponseEntity(HttpStatus.OK);
    }


}
