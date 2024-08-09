package org.example;

import java.util.Map;

public class Order {
    User user;
    Address deliveryAddress;
    Map<Integer, Integer> productAndCountMap;
    Warehouse warehouse;
    Invoice invoice;
    Payment payment;
    OrderStatus orderStatus;

    Order(User user, Warehouse warehouse) {
        this.user = user;
        this.productAndCountMap = user.getUserCart().getCartItems();
        this.warehouse = warehouse;
        this.deliveryAddress = user.address;
        invoice = new Invoice();
        invoice.generateInvoice(this);
        this.orderStatus = OrderStatus.UNDELIVERED;
    }

    public void checkout() {
        // 1. update inventory
        warehouse.removeItemFromInventory(productAndCountMap);

        // 2. make payment
        boolean isPaymentSucess = makePayment(new UPIPaymentMode());

        // 3. make cart empty
        if(isPaymentSucess) {
            user.getUserCart().emptyCart();
        } else {
            warehouse.addItemToInventory(productAndCountMap);
        }
    }

    public boolean makePayment(PaymentMode paymentMode) {
        payment= new Payment(paymentMode);
        return payment.makePayment();
    }

    public void generateOrderInvoice() {
        invoice.generateInvoice(this);
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", deliveryAddress=" + deliveryAddress +
                ", productAndCountMap=" + productAndCountMap +
                ", warehouse=" + warehouse +
                ", invoice=" + invoice +
                ", payment=" + payment +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
