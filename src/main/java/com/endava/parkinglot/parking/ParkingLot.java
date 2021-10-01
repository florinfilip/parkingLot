package com.endava.parkinglot.parking;

import com.endava.parkinglot.repositories.ParkingSpotRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@RequiredArgsConstructor
public class ParkingLot {


     ParkingSpotRepository parkingSpotRepository;

    private int id;
    private String name;
    private String address;
    private Set<ParkingSpot> parkingSpots = parkingSpotRepository.findAll()
            .stream()
            .collect(Collectors.toSet());



}
