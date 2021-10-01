package com.endava.parkinglot.reservation;

import com.endava.parkinglot.parking.ParkingSpot;
import com.endava.parkinglot.parking.ticket.ParkingTicketStatus;
import com.endava.parkinglot.repositories.ReservationRepository;
import com.endava.parkinglot.repositories.TicketRepository;
import com.endava.parkinglot.repositories.UserRepository;
import com.endava.parkinglot.reservation.Reservation;
import com.endava.parkinglot.user.User;
import com.endava.parkinglot.vehicle.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservationTest {


    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private TicketRepository ticketRepository;

    @Test
    @Rollback(value = false)
   public void it_should_create_reservation(){
        Reservation reservation = new Reservation();
        reservation.setExpiresAt(LocalDateTime.now());
        reservation.setIssuedAt(LocalDateTime.now());
        reservation.setVehicle(testEntityManager.find(Vehicle.class,1));
        reservation.setParkingSpot(testEntityManager.find(ParkingSpot.class,3));
        reservation.setUser(testEntityManager.find(User.class,16));
        reservation.setStatus(ParkingTicketStatus.ACTIVE);

        reservationRepository.save(reservation);
        System.out.println(reservation.getVehicle());
        System.out.println(reservationRepository.findAll().stream().collect(Collectors.toList()).toString());
        assertThat(reservationRepository.findAll().size(),is(1));

    }

    @Test
    void testGetReservations(){
       System.out.println(reservationRepository.findAll().stream().collect(Collectors.toList()).toString());
    }

    @Test
    void shouldPrintReservationsByUser(){
        System.out.println(reservationRepository.findReservationsByUser(userRepository.findById(16).get()));
    }




}
