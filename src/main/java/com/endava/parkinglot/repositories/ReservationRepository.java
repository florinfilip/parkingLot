package com.endava.parkinglot.repositories;

import com.endava.parkinglot.reservation.Reservation;
import com.endava.parkinglot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    List<Reservation> findReservationsByUser(User user);
}
