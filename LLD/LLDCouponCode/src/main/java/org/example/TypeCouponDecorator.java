package org.example;

import java.util.ArrayList;
import java.util.List;

public class TypeCouponDecorator extends CouponDecorator {
    Product product;
    int discountPercentage;
    ProductType type;

    static List<ProductType> eligibleTypes = new ArrayList<>();
    static {
        eligibleTypes.add(ProductType.FURNITURE_GOODS);
        eligibleTypes.add(ProductType.DECORATIVE_GOODS);
    }

    TypeCouponDecorator(Product product, int percentage, ProductType productType) {
        this.product = product;
        this.discountPercentage = percentage;
        this.type = productType;
    }


    @Override
    public double getPrice() {
        double price = product.getPrice();
        if(eligibleTypes.contains(this.type)) {
            return price - (price * discountPercentage)/100;
        }
        return price;
    }
}
