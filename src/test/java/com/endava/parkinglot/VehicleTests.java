package com.endava.parkinglot;


import com.endava.parkinglot.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.Collectors;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class VehicleTests {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void testGetVehicles(){

        System.out.println(vehicleRepository.findAll().stream().collect(Collectors.toList()));
    }

}
