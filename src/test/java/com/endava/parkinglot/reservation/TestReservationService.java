package com.endava.parkinglot.reservation;

import com.endava.parkinglot.parking.ParkingSpot;
import com.endava.parkinglot.parking.ticket.ParkingTicketStatus;
import com.endava.parkinglot.repositories.ReservationRepository;
import com.endava.parkinglot.repositories.UserRepository;
import com.endava.parkinglot.reservation.Reservation;
import com.endava.parkinglot.service.ReservationService;
import com.endava.parkinglot.user.User;
import com.endava.parkinglot.vehicle.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReservationService {

@Autowired
    private ReservationService reservationService;
@Autowired
private EntityManager entityManager;
@Autowired
    UserRepository userRepository;

@MockBean
    private ReservationRepository reservationRepository;


@Test
@Rollback(value = false)
    public void should_create_reservation_and_ticket(){

    Reservation reservation = new Reservation();
    reservation.setExpiresAt(LocalDateTime.now());
    reservation.setIssuedAt(LocalDateTime.now());
    reservation.setVehicle(entityManager.find(Vehicle.class,1));
    reservation.setParkingSpot(entityManager.find(ParkingSpot.class,3));
    reservation.setUser(entityManager.find(User.class,16));
    reservation.setStatus(ParkingTicketStatus.ACTIVE);


    reservationService.saveReservation(reservation);

}



}
