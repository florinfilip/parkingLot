package com.endava.parkinglot.controllers;

import com.endava.parkinglot.reservation.Reservation;
import com.endava.parkinglot.service.ReservationService;
import com.endava.parkinglot.service.UserService;
import com.endava.parkinglot.user.MyUserDetails;
import com.endava.parkinglot.user.User;
import com.endava.parkinglot.utils.Mappings;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.endava.parkinglot.utils.Mappings.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(RESERVATIONS)
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;


    @GetMapping("/all")
    public ResponseEntity getReservations(){
        return new ResponseEntity(reservationService.findAllReservations(),HttpStatus.OK);

    }

    @PostMapping("/new")
    public ResponseEntity createReservation(@RequestBody Reservation reservation){
        reservation.setUser(getUserPrincipal());
return new ResponseEntity(reservationService.saveReservation(reservation),HttpStatus.CREATED);
    }

    @DeleteMapping(DELETE+"/{id}")
            public ResponseEntity deleteReservation(@PathVariable int id){
         reservationService.deleteReservation(id);
return new ResponseEntity(HttpStatus.GONE);
            }

    @DeleteMapping(DELETE+"/user"+"/{id}")
    public ResponseEntity deleteReservationAsUser(@PathVariable int id){
        reservationService.deleteReservationAsUser(getUserPrincipal(),id);
        return new ResponseEntity(HttpStatus.GONE);
    }


    @PutMapping(UPDATE)
    public ResponseEntity updateReservation(@RequestBody Reservation reservation){
        return new ResponseEntity(reservationService.updateReservation(reservation),HttpStatus.OK);
    }



    @PutMapping("/pay/{id}")
    public ResponseEntity payReservation(@PathVariable int id){

        User user = getUserPrincipal();
        return new ResponseEntity(reservationService.payReservation(user,id),HttpStatus.OK);
    }

    @GetMapping("/userReservation")
    public ResponseEntity getUserReservations(){
        User user = getUserPrincipal();
        return new ResponseEntity(reservationService.findUserReservations(user),HttpStatus.OK);
    }


    public User getUserPrincipal(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
    User user = userDetails.getUser();
    return user;
}
}
