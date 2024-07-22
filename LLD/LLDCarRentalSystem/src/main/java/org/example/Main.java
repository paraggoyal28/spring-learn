package org.example;

import org.example.Product.Bike;
import org.example.Product.Car;
import org.example.Product.Vehicle;
import org.example.Product.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<User> users = addUsers();
        List<Vehicle> vehicles = addVehicles();
        List<Store> stores = addStores(vehicles);

        VehicleRentalSystem rentalSystem = new VehicleRentalSystem(stores, users);

        // 0. User comes
        User user = users.get(0);
        System.out.println("User on site " + user);

        // 1. user search store based on location
        Location location = new Location(403012, "Bangalore", "Karnataka", "India");
        System.out.println("Location searched " + location);

        Store store = rentalSystem.getStore(location);
        System.out.println("Store selected by user " + store);

        // 2. Get all vehicles you are interested in
        List<Vehicle> storeVehicles = store.getVehicles(VehicleType.CAR);
        System.out.println("Vehicles selected by user " + storeVehicles);

        // 3. reserving the particular vehicle
        Reservation reservation = store.createReservation(storeVehicles.getFirst(), users.getFirst());
        System.out.println("Reservation " + reservation);


        // 4. generate the bill
        Bill bill = new Bill(reservation);
        System.out.println(bill);

        // 5. make payment
        Payment payment = new Payment();
        payment.payBill(bill);

        // 6. trip completed, submit the vehicle and close the reservation
        store.completeReservation(reservation.getReservationId());

        // 7. check the reservation status
        System.out.println(reservation);

        // 8. check bill status
        System.out.println(bill);
    }

    public static List<User> addUsers(){

        List<User> users = new ArrayList<>();
        User user1 = new User(1, "Parag");
        users.add(user1);
        return users;
    }


    public static List<Vehicle> addVehicles(){

        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle vehicle1 = new Car();
        vehicle1.setVehicleID(1);
        vehicle1.setDailyRentalCost(400);
        vehicle1.setHourlyRentalCost(40);
        vehicle1.setVehicleType(VehicleType.CAR);

        Vehicle vehicle2 = new Bike();
        vehicle2.setVehicleID(2);
        vehicle2.setDailyRentalCost(200);
        vehicle2.setHourlyRentalCost(20);
        vehicle2.setVehicleType(VehicleType.BIKE);

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        return vehicles;
    }

    public static List<Store> addStores(List<Vehicle> vehicles){

        List<Store> stores = new ArrayList<>();
        Location location = new Location(403012, "Bangalore", "Karnataka", "India");

        Store store1 = new Store();
        store1.storeId=1;
        store1.setStoreLocation(location);
        store1.setVehicles(vehicles);

        stores.add(store1);
        return stores;
    }



}