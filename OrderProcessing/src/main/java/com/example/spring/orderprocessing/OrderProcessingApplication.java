package com.example.spring.orderprocessing;

import com.example.spring.orderprocessing.model.ShoppingCart;
import com.example.spring.orderprocessing.service.impl.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderProcessingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessingApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx, OrderService orderService) {
        return args -> {
            System.out.println("\n=== APPLICATION READY TO PROCESS ORDERS =====\n");

            // Execute business logic using injected services
            orderService.processOrder(100.0);

            // Concept 6: Providing prototype bean scope behaviour
            System.out.println("\n=== Testing prototype Scope ==== ");
            ShoppingCart cart1 = ctx.getBean(ShoppingCart.class);
            ShoppingCart cart2 = ctx.getBean(ShoppingCart.class);
            System.out.println("Are cart1 and cart2 same reference? " + (cart1 == cart2)); // Output: false

            System.out.println("\n--- Shutting Down Application Context ---");
        };
    }
}
