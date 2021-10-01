package com.endava.parkinglot.service;

import com.endava.parkinglot.parking.ticket.ParkingTicket;
import com.endava.parkinglot.parking.ticket.ParkingTicketDetails;
import com.endava.parkinglot.reservation.Reservation;

import java.util.List;

public interface TicketService {

    List<ParkingTicket> getParkingTickets();
    void createParkingTicketWithoutReservation(ParkingTicket parkingTicket);
    ParkingTicket createParkingTicketWithReservation(Reservation reservation);
    ParkingTicket setAndSaveTicketDetails(ParkingTicketDetails ticketDetails);
    void deleteParkingTicket(int id);
    void updateParkingTicket(ParkingTicket parkingTicket);
    boolean ticketExistsOrThrowException(int id);
}
