package org.example;

public class PercentageCouponDecorator extends CouponDecorator {

    Product product;
    int discountPercentage;

    PercentageCouponDecorator(Product product, int percentage) {
        this.product = product;
        this.discountPercentage = percentage;
    }



    @Override
    public double getPrice() {
        double price = product.getPrice();
        return price - (price * discountPercentage)/100;
    }
}
