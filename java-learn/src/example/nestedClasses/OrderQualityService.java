package example.nestedClasses;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OrderQualityService {
    
    // static nested class acting as a criteria holder
    public static class OrderFilterCriteria {
        private final String customerId;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final double minAmount;

        private OrderFilterCriteria(Builder builder) {
            this.customerId = builder.customerId;
            this.startDate = builder.startDate;
            this.endDate = builder.endDate;
            this.minAmount = builder.minAmount;
        }

        public String getCustomerId() {
            return customerId;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public double getMinAmount() {
            return minAmount;
        }

        public static class Builder {
            private String customerId;
            private LocalDate startDate;
            private LocalDate endDate;
            private double minAmount = 0.0;

            public Builder customerId(String customerId) {
                this.customerId = customerId;
                return this;
            }

            public Builder dateRange(LocalDate startDate, LocalDate endDate) {
                this.startDate = startDate;
                this.endDate = endDate;
                return this;
            }

            public Builder minAmount(double minAmount) {
                this.minAmount = minAmount;
                return this;
            }
            
            public OrderFilterCriteria build() {
                return new OrderFilterCriteria(this);
            }
        }
    }

    public List<String> searchOrders(OrderFilterCriteria criteria) {
        System.out.println("Order of customer: " + criteria.getCustomerId());
        System.out.println("Cost incurred by customer: " + criteria.getMinAmount());
        return List.of("ORD-101", "ORD-102");
    }

    public static void main(String[] args) {
        OrderQualityService.OrderFilterCriteria criteria = 
            new OrderQualityService.OrderFilterCriteria.Builder()
                .customerId("CUST-101")
                .minAmount(150.00)
                .build();
        OrderQualityService service = new OrderQualityService();
        List<String> orders = service.searchOrders(criteria);
        orders.forEach(System.out::println);
    }
}

