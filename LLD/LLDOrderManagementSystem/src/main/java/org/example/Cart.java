package org.example;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Integer, Integer> productVsCountMap;

    Cart() {
        productVsCountMap = new HashMap<>();
    }

    // add item to the cart
    public void addItemInCart(int productId, int count) {
        System.out.println("Adding items to the cart");
        if(productVsCountMap.containsKey(productId)) {
            productVsCountMap.compute(productId, (k, noOfItemsInCart) -> noOfItemsInCart + count);
        } else {
            productVsCountMap.put(productId, count);
        }
        System.out.println("Items in cart " + productVsCountMap.get(productId));
    }

    // remove items from cart
    public void removeItemFromCart(int productId, int count) {
        if(productVsCountMap.containsKey(productId)) {
            int noOfItemsInCart = productVsCountMap.get(productId);
            if(count - noOfItemsInCart == 0) {
                productVsCountMap.remove(productId);
            } else {
                productVsCountMap.put(productId, noOfItemsInCart - count);
            }
        }
    }

    // empty my cart
    public void emptyCart() {
        productVsCountMap = new HashMap<>();
    }

    // View cart
    public Map<Integer, Integer> getCartItems() {
        return productVsCountMap;
    }

}
