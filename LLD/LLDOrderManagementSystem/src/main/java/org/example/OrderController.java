package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController {
    List<Order> orderList;
    Map<Integer, List<Order>> userIDVsOrders;

    OrderController() {
        orderList = new ArrayList<>();
        userIDVsOrders = new HashMap<>();
    }

    // create new order
    public Order createNewOrder(User user, Warehouse warehouse) {
        Order order = new Order(user, warehouse);
        orderList.add(order);

        List<Order> userOrders = userIDVsOrders.getOrDefault(user.id, new ArrayList<>());
        userOrders.add(order);
        print(userOrders);
        userIDVsOrders.put(user.id, userOrders);
        return order;
    }

    private void print(List<Order> orders) {
        for(Order order: orders) {
            System.out.println("Order " + order);
        }
    }

    // remove order
    public void removeOrder(Order order) {

        // remove order capability goes here
    }

    public List<Order> getOrderByCustomerId(int userId) {
        return null;
    }

    public Order getOrderByOrderId(int orderId) {
        return null;
    }
}
