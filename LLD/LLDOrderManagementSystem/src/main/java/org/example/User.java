package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    int id;
    String name;
    Address address;
    Cart userCartDetails;
    List<Integer> orderIds;

    public User() {
        userCartDetails = new Cart();
        orderIds = new ArrayList<>();
    }

    // get User Cart
    public Cart getUserCart() {
        return userCartDetails;
    }
}
