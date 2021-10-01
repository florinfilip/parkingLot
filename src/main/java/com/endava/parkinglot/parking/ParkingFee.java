package com.endava.parkinglot.parking;

import com.endava.parkinglot.vehicle.Size;
import lombok.Data;

@Data
public class ParkingFee {
private int id;
private ParkingLot parkingLot;
private int hoursParked;
private double taxPerHour;
private Size size;
private String amount;


}
