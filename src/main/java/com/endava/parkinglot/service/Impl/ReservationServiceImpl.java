package com.endava.parkinglot.service.Impl;

import com.endava.parkinglot.parking.ticket.ParkingTicketStatus;
import com.endava.parkinglot.repositories.ReservationRepository;
import com.endava.parkinglot.reservation.Reservation;
import com.endava.parkinglot.service.ReservationService;
import com.endava.parkinglot.service.TicketService;
import com.endava.parkinglot.service.UserService;
import com.endava.parkinglot.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final TicketService ticketService;
    private final ParkingSpotServiceImpl parkingSpotService;

    @Override
    public Reservation saveReservation(Reservation newReservation) {

        Reservation reservation = reservationRepository.findById(newReservation.getId()).orElse(new Reservation());
        reservation.setUser(newReservation.getUser());
        reservation.setVehicle(newReservation.getVehicle());
        reservation.setParkingSpot(parkingSpotService.findProperSpotForVehicle(newReservation.getVehicle().getSize(),newReservation.getVehicle().getType()));
        reservation.setIssuedAt(newReservation.getIssuedAt());
        reservation.setExpiresAt(newReservation.getExpiresAt());
        reservation.setStatus(ParkingTicketStatus.ACTIVE);

        reservation.setParkingTicket(ticketService.createParkingTicketWithReservation(reservation));
        return reservationRepository.save(reservation);
    }



    @Override
    public List<Reservation> findAllReservations() {

        return reservationRepository.findAll().stream().collect(Collectors.toList());

    }
    @Override
    public List<Reservation> findUserReservations(User user){
        return reservationRepository.findReservationsByUser(user);

    }

    @Override
    public void deleteReservation(int id){
        reservationExistsOrThrowException(id);
        if (reservationRepository.findById(id).get().getStatus().equals("ACTIVE"))
            throw new RuntimeException("Reservation is UNPAID");
        reservationRepository.deleteById(id);
    }


    @Override
    public Reservation payReservation(User user, int id)
    {
        reservationBelongsToUserOrThrowException(user, id);
        Reservation reservation = reservationRepository.findById(id).get();

       if(reservation.getStatus().equals(ParkingTicketStatus.ACTIVE))
       {reservation.setStatus(ParkingTicketStatus.PAID);
           reservation.getParkingTicket().setStatus(ParkingTicketStatus.PAID);
       reservationRepository.save(reservation);


       }
       else {
           throw new RuntimeException("The reservation is not active!");
       }
       return reservation;
    }


    @Override
    public void deleteReservationAsUser(User user, int id){

        List<Reservation> reservationList =reservationRepository.findReservationsByUser(user);
        Reservation reservation = reservationRepository.findById(id).get();
        if(reservationList.contains(reservation) && isReservationValidToCancel(reservation)){
            deleteReservation(id);
        }

       else throw new RuntimeException("Reservation not found for user "+user.getUsername()+"!");

    }


    @Override
    public Reservation updateReservation(Reservation reservation){
reservationExistsOrThrowException(reservation.getId());
return saveReservation(reservation);
    }

    public boolean reservationExistsOrThrowException(int id){

         if(!reservationRepository.findById(id).isPresent())
             throw new RuntimeException("Reservation not found!");

         return true;


    }

    public boolean reservationBelongsToUserOrThrowException(User user, int id){
      if(reservationRepository.findById(id).get().getUser().equals(user))
          return true;
      else
          throw new RuntimeException("Reservation not found for user " + user.getUsername() + "!");

    }



    public boolean isReservationValidToCancel(Reservation reservation){
        int daysToReservation = reservation.getIssuedAt().compareTo(LocalDateTime.now());
        if(daysToReservation<=3){
            throw new RuntimeException("Cannot cancel, less than three days until reservation! You have " + daysToReservation+" days to the reservation.");
        }
else return true;
    }



}
