package org.example;

import org.example.Product.Vehicle;

import java.util.Date;

public class Reservation {
    int reservationId;
    User user;
    Vehicle vehicle;
    Date bookingDate;
    Date dateBookedFrom;
    Date dateBookedTo;
    Long fromTimeStamp;
    Long toTimeStamp;
    Location pickUpLocation;
    Location dropLocation;
    ReservationType reservationType;
    ReservationStatus reservationStatus;
    Location location;


    public static Reservation createReservation(User user, Vehicle vehicle, Location location) {
        // generate new id
        Reservation reservation = new Reservation();
        reservation.reservationId = 12232;
        reservation.user = user;
        reservation.vehicle = vehicle;
        reservation.reservationType = ReservationType.DAILY;
        reservation.reservationStatus = ReservationStatus.SCHEDULED;
        reservation.bookingDate = new Date();
        reservation.dateBookedFrom = new Date();
        reservation.dateBookedTo = new Date(reservation.dateBookedFrom.getTime() +  2*24*60*60*1000);
        reservation.fromTimeStamp = reservation.dateBookedFrom.getTime();
        reservation.toTimeStamp = reservation.dateBookedTo.getTime();
        reservation.pickUpLocation = location;
        reservation.location = location;
        return reservation;
    }
    // other CRUD operations

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDateBookedFrom() {
        return dateBookedFrom;
    }

    public void setDateBookedFrom(Date dateBookedFrom) {
        this.dateBookedFrom = dateBookedFrom;
    }

    public Date getDateBookedTo() {
        return dateBookedTo;
    }

    public void setDateBookedTo(Date dateBookedTo) {
        this.dateBookedTo = dateBookedTo;
    }

    public Long getFromTimeStamp() {
        return fromTimeStamp;
    }

    public void setFromTimeStamp(Long fromTimeStamp) {
        this.fromTimeStamp = fromTimeStamp;
    }

    public Long getToTimeStamp() {
        return toTimeStamp;
    }

    public void setToTimeStamp(Long toTimeStamp) {
        this.toTimeStamp = toTimeStamp;
    }

    public void completeReservation() {
        this.reservationStatus = ReservationStatus.COMPLETED;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", user=" + user +
                ", vehicle=" + vehicle +
                ", bookingDate=" + bookingDate +
                ", dateBookedFrom=" + dateBookedFrom +
                ", fromTimeStamp=" + fromTimeStamp +
                ", pickUpLocation=" + pickUpLocation +
                ", reservationType=" + reservationType +
                ", reservationStatus=" + reservationStatus +
                ", location=" + location +
                '}';
    }
}
