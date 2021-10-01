package com.endava.parkinglot.parking.ticket;

import com.endava.parkinglot.parking.ParkingFee;
import com.endava.parkinglot.parking.ParkingSpot;
import com.endava.parkinglot.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class ParkingTicketDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpot parkingSpot;

    @JsonFormat(pattern = "dd-MM-yyyy h:mm a", shape = JsonFormat.Shape.STRING)
    @Column(name = "issued_at", nullable = false)
    private LocalDateTime issuedAt;

    @JsonFormat(pattern = "dd-MM-yyyy h:mm a", shape = JsonFormat.Shape.STRING)
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @OneToOne(optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Transient
    private ParkingFee parkingFee;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ParkingTicketStatus status=ParkingTicketStatus.ACTIVE;


}
