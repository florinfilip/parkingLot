package com.endava.parkinglot.repositories;

import com.endava.parkinglot.parking.ParkingSpot;
import com.endava.parkinglot.parking.Type;
import com.endava.parkinglot.reservation.Reservation;
import com.endava.parkinglot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Integer> {

    Optional<ParkingSpot> findParkingSpotByNumber(int number);
    Optional<List<ParkingSpot>> findParkingSpotsByType(Type type);
    Optional<List<ParkingSpot>> findParkingSpotsByFree(boolean free);

}
