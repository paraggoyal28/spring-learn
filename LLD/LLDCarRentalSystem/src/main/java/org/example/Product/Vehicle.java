package org.example.Product;

import java.util.Date;

public class Vehicle {
    int vehicleID;
    int vehicleNumber;
    VehicleType vehicleType;
    String companyName;
    String modelName;
    int kmDriven;
    Date manufacturingDate;
    int average;
    int cc;
    int dailyRentalCost;
    int hourlyRentalCost;
    int noOfSeat;
    Status status;

    public Vehicle() {}

    public Vehicle(int vehicleID, int vehicleNumber, VehicleType vehicleType, String companyName, String modelName, int kmDriven, Date manufacturingDate, int average, int cc, int dailyRentalCost, int hourlyRentalCost, int noOfSeat, Status status) {
        this.vehicleID = vehicleID;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.companyName = companyName;
        this.modelName = modelName;
        this.kmDriven = kmDriven;
        this.manufacturingDate = manufacturingDate;
        this.average = average;
        this.cc = cc;
        this.dailyRentalCost = dailyRentalCost;
        this.hourlyRentalCost = hourlyRentalCost;
        this.noOfSeat = noOfSeat;
        this.status = status;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(int kmDriven) {
        this.kmDriven = kmDriven;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public int getDailyRentalCost() {
        return dailyRentalCost;
    }

    public void setDailyRentalCost(int dailyRentalCost) {
        this.dailyRentalCost = dailyRentalCost;
    }

    public int getHourlyRentalCost() {
        return hourlyRentalCost;
    }

    public void setHourlyRentalCost(int hourlyRentalCost) {
        this.hourlyRentalCost = hourlyRentalCost;
    }

    public int getNoOfSeat() {
        return noOfSeat;
    }

    public void setNoOfSeat(int noOfSeat) {
        this.noOfSeat = noOfSeat;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID=" + vehicleID +
                ", vehicleType=" + vehicleType +
                ", dailyRentalCost=" + dailyRentalCost +
                ", hourlyRentalCost=" + hourlyRentalCost +
                '}';
    }
}
