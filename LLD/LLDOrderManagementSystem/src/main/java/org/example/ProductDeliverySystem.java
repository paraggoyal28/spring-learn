package org.example;

import java.util.List;

public class ProductDeliverySystem {
    UserController userController;
    WarehouseController warehouseController;
    OrderController orderController;

    ProductDeliverySystem(List<User> userList, List<Warehouse> warehouseList) {
        userController = new UserController(userList);
        warehouseController = new WarehouseController(warehouseList, null);
        orderController = new OrderController();
    }

    // get user object
    public User getUser(int userId) {
        return userController.getUser(userId);
    }

    // get warehouse
    public Warehouse getWarehouse(WarehouseSelectionStrategy warehouseSelectionStrategy) {
        return warehouseController.selectWarehouse(warehouseSelectionStrategy);
    }

    // get inventory
    public Inventory getInventory(Warehouse warehouse) {
        System.out.println("Fetching Inventory");
        return warehouse.inventory;
    }

    // add product to cart
    public void addProductToCart(User user, Product product, int count) {
        Cart cart = user.getUserCart();
        cart.addItemInCart(product.id, count);
    }

    // place order
    public Order placeOrder(User user, Warehouse warehouse) {
        return orderController.createNewOrder(user, warehouse);
    }

    // checkout
    public void checkout(Order order) {
        order.checkout();
    }
}
