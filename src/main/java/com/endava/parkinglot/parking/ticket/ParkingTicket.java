package com.endava.parkinglot.parking.ticket;

import com.endava.parkinglot.parking.ParkingFee;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(schema = "parking",
         name = "tickets")
public class ParkingTicket extends ParkingTicketDetails {

    @Transient
    private ParkingFee parkingFee;

}
