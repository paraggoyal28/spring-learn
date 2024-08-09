package org.example;

public class Product {
    int id;
    String name;
    int count;
    double price;

    Product() {
        this.count = 0;
    }

    // add products
    public void addProduct(int count) {
        this.count += count;
    }

    // remove products
    public void removeProduct(int count) {
        System.out.println("Initial Product count " + this.count);
        if(count <= this.count)
        this.count -= count;
        else
            throw new RuntimeException("Cannot remove more than current items");
        System.out.println("Final Product Count " + this.count);
    }

    public int getProducts() {
        return count;
    }
}
