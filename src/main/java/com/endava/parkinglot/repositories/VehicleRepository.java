package com.endava.parkinglot.repositories;

import com.endava.parkinglot.reservation.Reservation;
import com.endava.parkinglot.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
}
