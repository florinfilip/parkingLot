package com.endava.parkinglot.service;

import com.endava.parkinglot.reservation.Reservation;
import com.endava.parkinglot.user.User;

import java.util.List;

public interface ReservationService {

    Reservation saveReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    List<Reservation> findAllReservations();
   List<Reservation> findUserReservations(User user);
    void deleteReservation(int id);
    void deleteReservationAsUser(User user, int id);
    Reservation payReservation(User user, int id);
    boolean reservationExistsOrThrowException(int id);


}
