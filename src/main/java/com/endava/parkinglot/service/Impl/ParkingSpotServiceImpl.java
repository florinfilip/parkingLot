package com.endava.parkinglot.service.Impl;

import com.endava.parkinglot.parking.ParkingSpot;
import com.endava.parkinglot.parking.Type;
import com.endava.parkinglot.repositories.ParkingSpotRepository;
import com.endava.parkinglot.service.ParkingSpotService;
import com.endava.parkinglot.user.User;
import com.endava.parkinglot.vehicle.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

import static javax.swing.text.StyleConstants.Size;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;


    @Override
    public List<ParkingSpot> getParkingSpots() {
        return parkingSpotRepository.findAll()
              .stream()
              .sorted(Comparator.comparing(ParkingSpot::getNumber))
              .collect(Collectors.toList());

    }

    @Override
    public ParkingSpot saveParkingSpot(ParkingSpot newParkingSpot) {

       ParkingSpot parkingSpot = parkingSpotRepository.findById(newParkingSpot.getId()).orElse(new ParkingSpot());

       parkingSpot.setNumber(newParkingSpot.getNumber());
       parkingSpot.setFree(true);
       parkingSpot.setSize(newParkingSpot.getSize());
       parkingSpot.setType(newParkingSpot.getType());
       parkingSpot.setFree(newParkingSpot.getFree());

       return parkingSpotRepository.save(parkingSpot);
    }

    @Override
    public ParkingSpot updateParkingSpot(ParkingSpot updatedParkingSpot) {
        parkingSpotExists(updatedParkingSpot.getId());
        return saveParkingSpot(updatedParkingSpot);
    }

    @Override
    public void deleteParkingSpot(int id) {
    parkingSpotRepository.deleteById(id);
    }




    public boolean parkingSpotExists(int id){
        if(parkingSpotRepository.findById(id).isPresent())
            return true;
        else throw new RuntimeException(String.format("Parking Spot with Id %s does not exist!", id));
    }


    public boolean isNumberAvailable(int number){
     if(parkingSpotRepository.findParkingSpotByNumber(number).isEmpty())
         return true;
     else throw new RuntimeException(String.format("Parking Spot number %s Already Exists!",  number ));
    }


    public List<ParkingSpot> findParkingSpotsByType(Type type){
        return parkingSpotRepository.findParkingSpotsByType(type).get();
    }


    public List<ParkingSpot> findParkingSpotsByFree(boolean free){
        return parkingSpotRepository.findParkingSpotsByFree(free).get();
    }

    public ParkingSpot findProperSpotForVehicle(Size size, Type type ){
List<ParkingSpot> parkingSpotsList = parkingSpotRepository.findParkingSpotsByTypeAndAndSize(type,size).get();
        return parkingSpotsList.stream()
                .filter(parkingSpot -> parkingSpot.getFree()
                        .equals(true))
                .findFirst()
                .get();




    }


}
