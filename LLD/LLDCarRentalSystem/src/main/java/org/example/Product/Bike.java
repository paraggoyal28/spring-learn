package org.example.Product;

import java.util.Date;

public class Bike extends Vehicle{

    public Bike() {super();}

    public Bike(int vehicleID, int vehicleNumber, VehicleType vehicleType, String companyName, String modelName, int kmDriven, Date manufacturingDate, int average, int cc, int dailyRentalCost, int hourlyRentalCost, int noOfSeat, Status status) {
        super(vehicleID, vehicleNumber, vehicleType, companyName, modelName, kmDriven, manufacturingDate, average, cc, dailyRentalCost, hourlyRentalCost, noOfSeat, status);
    }
}
