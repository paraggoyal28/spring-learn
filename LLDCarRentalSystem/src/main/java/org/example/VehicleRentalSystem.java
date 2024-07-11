package org.example;

import java.util.List;

public class VehicleRentalSystem {
    List<Store> stores;
    List<User> users;

    public VehicleRentalSystem(List<Store> stores, List<User> users) {
        this.stores = stores;
        this.users = users;
    }

    public Store getStore(Location location) {
        // based on location, filte the store
        return stores.get(0);
    }

    // CRUD operations on stores and users.
}
