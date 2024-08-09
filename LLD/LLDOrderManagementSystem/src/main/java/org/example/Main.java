package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Main mainObj = new Main();

        // 1. create warehouses in the system
        List<Warehouse> warehouseList = new ArrayList<>();
        warehouseList.add(mainObj.addWarehouseAndItsInventory());

        // 2. create users in the system
        List<User> userList = new ArrayList<>();
        userList.add(mainObj.createUser());

        // 3. feed the system with the initial information
        ProductDeliverySystem productDeliverySystem = new ProductDeliverySystem(userList, warehouseList);

        mainObj.runDeliveryFlow(productDeliverySystem, 1);

    }

    private Warehouse addWarehouseAndItsInventory() {
        Warehouse warehouse = new Warehouse();

        Inventory inventory = new Inventory();

        inventory.addCategory(0001, "Peppsi Large Cold Drink", 100);
        inventory.addCategory(0002, "Doove small soap", 50);

        // create 3 products

        inventory.addProducts(2, 0001);
        inventory.addProducts(1, 0002);

        warehouse.inventory = inventory;
        return warehouse;
    }

    private User createUser() {
        User user = new User();
        user.id = 1;
        user.name = "P.G";
        user.address = new Address(560043, "Bangalore", "Karnataka");
        return user;
    }

    private void runDeliveryFlow(ProductDeliverySystem productDeliverySystem, int userId) {

        // 1. Get the user object
        User user = productDeliverySystem.getUser(userId);

        // 2. Get the warehouse based on user preference
        Warehouse warehouse = productDeliverySystem.getWarehouse(new NearestWarehouseSelectionStrategy());

        // 3. Get all the inventory to show the user
        Inventory inventory = productDeliverySystem.getInventory(warehouse);

        Product productIWantToOrder = null;
        for(Product product: inventory.productList) {
            if(product.name.equals("Peppsi Large Cold Drink")) {
                productIWantToOrder = product;
            }
        }

        // 4. add product to the cart
        productDeliverySystem.addProductToCart(user, productIWantToOrder, 2);

        // 5. place order
        Order order = productDeliverySystem.placeOrder(user, warehouse);

        // 6. checkout
        productDeliverySystem.checkout(order);


    }

}