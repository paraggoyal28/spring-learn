package org.example;

import org.example.Product.Vehicle;

public class Bill {
    Reservation reservation;
    double totalBillAmount;
    boolean isBillPaid;

    public Bill(Reservation reservation) {
        this.reservation = reservation;
        this.isBillPaid = false;
        this.totalBillAmount = this.computeBillAmount();
    }

    private double computeBillAmount() {
        Vehicle rentedVehicle = reservation.getVehicle();
        long daysRented = (reservation.getDateBookedTo().getTime() - reservation.getDateBookedFrom().getTime())/(1000 * 60 * 60 * 24);
        long hoursRented = (reservation.getDateBookedTo().getTime() - reservation.getDateBookedFrom().getTime())/(1000 * 60 * 60);
        if(reservation.reservationType == ReservationType.DAILY) {
            return rentedVehicle.getDailyRentalCost() * daysRented;
        }
        return  rentedVehicle.getHourlyRentalCost() * hoursRented;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "reservation=" + reservation +
                ", totalBillAmount=" + totalBillAmount +
                ", isBillPaid=" + isBillPaid +
                '}';
    }
}
