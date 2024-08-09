package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory {

    List<Product> productList;


    Inventory() {
        productList = new ArrayList<>();
    }

    // add new Category
    public void addCategory(int id, String name, double price) {
        Product product = new Product();
        product.price = price;
        product.name = name;
        product.id = id;
        productList.add(product);
    }

    // add product to the particular category
    public void addProducts(int count, int productId) {
        Product selectedProduct = null;
        for(Product product: productList) {
            if(product.id == productId) {
                selectedProduct = product;
            }
        }
        if(selectedProduct!=null) {
            selectedProduct.addProduct(count);
        }
    }

    // remove items from the category
    public void removeItems(Map<Integer, Integer> productAndCountMap) {
        for(Map.Entry<Integer, Integer> entry: productAndCountMap.entrySet()) {
            Product product = getProductById(entry.getKey());
            product.removeProduct(entry.getValue());
        }
    }

    private Product getProductById(int productId) {
        for(Product product: productList) {
            if(product.id == productId) {
                return product;
            }
        }

        return null;
    }


}
