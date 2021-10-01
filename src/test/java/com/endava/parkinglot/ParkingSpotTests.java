package com.endava.parkinglot;

import com.endava.parkinglot.repositories.ParkingSpotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParkingSpotTests {
    @Autowired
   private ParkingSpotRepository parkingSpotRepository;

    @Test
    void getParkingSpotsTest(){
        parkingSpotRepository.findAll().stream().collect(Collectors.toList());
    }

    @Test
    void displayDaysBetweenTwoDates(){
        LocalDateTime date1=LocalDateTime.of(2021,10,15,16,55);
        LocalDateTime date2= LocalDateTime.of(2021,10,7,15,12);

        long daysBetween= ChronoUnit.DAYS.between(date2,date1);
        date1.compareTo(date2);
        System.out.println(daysBetween);
        System.out.println(date1.compareTo(date2));
    }

}
