package com.endava.parkinglot.service;

import com.endava.parkinglot.parking.ParkingSpot;
import com.endava.parkinglot.parking.Type;
import com.endava.parkinglot.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface ParkingSpotService {

     List<ParkingSpot> getParkingSpots();
     ParkingSpot saveParkingSpot(ParkingSpot parkingSpot);
     void deleteParkingSpot(int id);
     ParkingSpot updateParkingSpot(ParkingSpot parkingSpot);
     boolean isNumberAvailable(int number);
     boolean parkingSpotExists(int id);
      List<ParkingSpot> findParkingSpotsByType(Type type);
      List<ParkingSpot> findParkingSpotsByFree(boolean free);
}
