package com.endava.parkinglot.service.Impl;

import com.endava.parkinglot.parking.ticket.ParkingTicket;
import com.endava.parkinglot.parking.ticket.ParkingTicketDetails;
import com.endava.parkinglot.repositories.TicketRepository;
import com.endava.parkinglot.reservation.Reservation;
import com.endava.parkinglot.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<ParkingTicket> getParkingTickets(){
       return ticketRepository.findAll().stream().collect(Collectors.toList());
    }


    @Override
    public void createParkingTicketWithoutReservation(ParkingTicket newParkingTicket) {
       setAndSaveTicketDetails(newParkingTicket);
    }


    public ParkingTicket createParkingTicketWithReservation(Reservation reservation){

        return setAndSaveTicketDetails(reservation);
    }



    @Override
    public void deleteParkingTicket(int id) {
        ticketExistsOrThrowException(id);
        ticketRepository.deleteById(id);

    }

    @Override
    public void updateParkingTicket(ParkingTicket parkingTicket) {

        ticketExistsOrThrowException(parkingTicket.getId());
        setAndSaveTicketDetails(parkingTicket);


    }

    public ParkingTicket setAndSaveTicketDetails(ParkingTicketDetails ticketDetails){
        ParkingTicket parkingTicket = ticketRepository.findById(ticketDetails.getId()).orElse(new ParkingTicket());
        parkingTicket.setParkingSpot(ticketDetails.getParkingSpot());
        parkingTicket.setVehicle(ticketDetails.getVehicle());
        parkingTicket.setIssuedAt(LocalDateTime.now());
        parkingTicket.setExpiresAt(ticketDetails.getExpiresAt());

        return ticketRepository.save(parkingTicket);
    }


    public boolean ticketExistsOrThrowException(int id){
        if(ticketRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ticket with id " + id +" does not exist!");
        }
        return true;
    }
}
