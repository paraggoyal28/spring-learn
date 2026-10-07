package example.newFeatures;


record Product(String id, String name, double price) {

    // 1. Compact Constructor 
    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be blank");
        }
        // compiler automatically assigns fields after this 
    }

    // 2. Custom Business Logic Method
    public double calculatedDiscountedPrice(double discountPercentage) {
        return price - ((price * discountPercentage) / 100.0);
    }

    // 3. Static Factory Method
    public static Product defaultProduct() {
        return new Product("PROD-101", "Generic Item", 9.99);
    }
}

// Usage
public class ProductDemo {
    public static void main(String[] args) {
        Product p = new Product("PROD-101", "Mechanical Keyboard", 120.0);

        System.out.println("Product name: ");
        System.out.println(p.name());

        System.out.println("Product Description: ");
        System.out.println(p);

        System.out.println("Discounted Price");
        System.out.println(p.calculatedDiscountedPrice(10));
    }
}
