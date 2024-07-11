package org.example;

import org.example.Product.Vehicle;
import org.example.Product.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Store {
    int storeId;
    VehicleInventoryManagement vehicleInventoryManagement;
    Location storeLocation;
    List<Reservation> reservations;

    public Store() {
        reservations = new ArrayList<>();
    }

    public List<Vehicle> getVehicles(VehicleType vehicleType) {
        return vehicleInventoryManagement.getVehicles(vehicleType);
    }

    public void setVehicles(List<Vehicle> vehicles) {
        vehicleInventoryManagement = new VehicleInventoryManagement(vehicles);
    }

    public Reservation createReservation(Vehicle vehicle, User user) {
        Reservation reservation = Reservation.createReservation(user, vehicle, storeLocation);
        reservations.add(reservation);
        return reservation;
    }

    public boolean completeReservation(int reservationId) {
        Reservation reservation = reservations.stream().filter(reservation1 -> reservation1.getReservationId() == reservationId).toList().getFirst();
        reservations.remove(reservation);
        reservation.completeReservation();
        return true;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                '}';
    }

    public void setStoreLocation(Location storeLocation) {
        this.storeLocation = storeLocation;
    }
}
