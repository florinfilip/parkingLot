package com.endava.parkinglot.reservation;

import com.endava.parkinglot.parking.ticket.ParkingTicket;
import com.endava.parkinglot.parking.ticket.ParkingTicketDetails;
import com.endava.parkinglot.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(schema = "parking",
        name = "reservations")
@Entity
public class Reservation extends ParkingTicketDetails {


    @OneToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(optional = false)
    @JoinColumn(name = "parking_ticket_id", nullable = false)
    private ParkingTicket parkingTicket;




}