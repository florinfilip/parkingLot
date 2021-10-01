package com.endava.parkinglot.parking;

import com.endava.parkinglot.vehicle.Size;
import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(schema = "parking",
        name = "parking_spots")
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "number", nullable = false)
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private Size size;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "free", nullable = false)
    private Boolean free = true;

}

