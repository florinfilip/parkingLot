package com.endava.parkinglot.controllers;

import com.endava.parkinglot.parking.ParkingSpot;
import com.endava.parkinglot.parking.Type;
import com.endava.parkinglot.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/parkingSpots")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    @GetMapping
    public List<ParkingSpot> getParkingSpots(){
        return parkingSpotService.getParkingSpots();
    }

    @GetMapping("/free/{free}")
    public List<ParkingSpot> getFreeParkingSpots(@PathVariable boolean free){
        return parkingSpotService.findParkingSpotsByFree(free);
    }
    @GetMapping(value = "/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ParkingSpot> getParkingSpotsByType(@PathVariable(value = "type") Type type){
        return parkingSpotService.findParkingSpotsByType(type);
    }


    @PostMapping("/new")
    public ResponseEntity addParkingSpot(@RequestBody ParkingSpot parkingSpot){
        return new ResponseEntity(
                parkingSpotService.saveParkingSpot(parkingSpot), HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity updateParkingSpot(@RequestBody ParkingSpot parkingSpot){
        return new ResponseEntity(
                parkingSpotService.updateParkingSpot(parkingSpot), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteParkingSpot(@PathVariable int id){
        parkingSpotService.deleteParkingSpot(id);
        return new ResponseEntity(HttpStatus.GONE);
    }
}
