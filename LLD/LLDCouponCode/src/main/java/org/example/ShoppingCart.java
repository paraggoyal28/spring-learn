package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addToCart(Product product) {
        Product productWithEligibleDiscount =
                    new TypeCouponDecorator(
                            new PercentageCouponDecorator(product, 10),
                            3,
                            product.getType()
                    );
        products.add(productWithEligibleDiscount);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for(Product product: products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
