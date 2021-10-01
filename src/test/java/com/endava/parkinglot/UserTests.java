package com.endava.parkinglot;

import com.endava.parkinglot.parking.ParkingSpot;
import com.endava.parkinglot.repositories.ReservationRepository;
import com.endava.parkinglot.repositories.RoleRepository;
import com.endava.parkinglot.repositories.UserRepository;
import com.endava.parkinglot.reservation.Reservation;
import com.endava.parkinglot.service.UserService;
import com.endava.parkinglot.user.User;
import com.endava.parkinglot.user.role.Role;
import com.endava.parkinglot.vehicle.Vehicle;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@DataJpaTest
public class UserTests {

@Autowired
    private UserRepository userRepository;
@Autowired
    private EntityManager testEntityManager;
@Autowired
private ReservationRepository reservationRepository;

private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


@Test
@Rollback(value = true)
    public void addUserInDatabaseTest(){
    com.endava.parkinglot.user.User newUser = new com.endava.parkinglot.user.User();
    newUser.setEmail("florin@yahoo.com");
    newUser.setUsername("Florin");
    newUser.setPassword(passwordEncoder.encode("Fuin"));
    newUser.setPhone("47214589");
    newUser.setRoles(Set.of(testEntityManager.find(Role.class,2)));
    newUser.setEnabled(true);
    userRepository.save(newUser);
    assertThat(userRepository.findUserByUsername("Florin").get().getUsername(),is("Florin"));


}

@Test
    public void testFindUsersInDatabase(){
    log.info(userRepository.findAll().stream().collect(Collectors.toList()).toString());
}


    @Test
    void testFindUserById(){
        System.out.println(userRepository.findById(0).get().toString());}



    @Test
    void testUserExist(){
       assertTrue( userRepository.findAll().stream()
                .filter(user->user.getUsername().equalsIgnoreCase("teodor"))
                .findAny()
                .isPresent());
    }

    @Test
    void testGetRoleNamesFromUser(){

     com.endava.parkinglot.user.User user = testEntityManager.find(com.endava.parkinglot.user.User.class,16);
        log.info(user.getRoles().stream()
                .map(role->role.getName())
                .collect(Collectors.toSet()).toString());
    }


    @Test
    @Rollback(value = true)
    void testCreateReservation(){
        Reservation reservation = new Reservation();
        reservation.setParkingSpot(testEntityManager.find(ParkingSpot.class,1));
        reservation.setUser(testEntityManager.find(com.endava.parkinglot.user.User.class,16));
        reservation.setVehicle(testEntityManager.find(Vehicle.class,1));
        reservation.setIssuedAt(LocalDateTime.now());
        reservation.setExpiresAt(LocalDateTime.of(2022,4,6,14,51));
        log.info(reservation.toString());
       reservationRepository.save(reservation);
        assertThat(reservationRepository.findAll().size(),is(1));
    }

    @Test
    @Rollback(value=true)
    void testUpdateOrCreateUser(){
        com.endava.parkinglot.user.User newUser = new com.endava.parkinglot.user.User();
        newUser.setEmail("florin@yahoo.com");
        newUser.setUsername("Florin");
        newUser.setPassword(passwordEncoder.encode("Fuin"));

        newUser.setRoles(Set.of(testEntityManager.find(Role.class,2)));
        newUser.setEnabled(true);

        com.endava.parkinglot.user.User existingUser = userRepository.findById(14).orElse(new com.endava.parkinglot.user.User());
        System.out.println(existingUser);
        existingUser.setUsername(
                Optional.ofNullable(newUser.getUsername())
                        .orElse(existingUser.getUsername()));
        existingUser.setPassword(
                Optional.ofNullable(newUser.getPassword())
                        .orElse(existingUser.getPassword()));
        existingUser.setEnabled(
                Optional.ofNullable(newUser.isEnabled())
                        .orElse(existingUser.isEnabled()));
        existingUser.setEmail(
                Optional.ofNullable(newUser.getEmail())
                        .orElse(existingUser.getEmail()));
        existingUser.setPhone(
                Optional.ofNullable(newUser.getPhone())
                        .orElse(existingUser.getPhone()));
        existingUser.setRoles(
                Optional.ofNullable(newUser.getRoles())
                        .orElse(existingUser.getRoles()));

        System.out.println(existingUser.toString());
    }




}







