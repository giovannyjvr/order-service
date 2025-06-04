package com.example.order.dto;

import java.util.UUID;

public class OrderResponseDTO {
    private UUID orderId;
    private Integer quantity;
    private String status;
    private CustomerInfo customer;
    private ProductInfo product;

    public OrderResponseDTO() {}

    public OrderResponseDTO(UUID orderId, Integer quantity, String status,
                            CustomerInfo customer, ProductInfo product) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.status = status;
        this.customer = customer;
        this.product = product;
    }

    public static class CustomerInfo {
        private UUID id;
        private String name;
        private String email;

        public CustomerInfo() {}
        public CustomerInfo(UUID id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    public static class ProductInfo {
        private UUID id;
        private String name;
        private Double price;

        public ProductInfo() {}
        public ProductInfo(UUID id, String name, Double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }
    }

    public UUID getOrderId() { return orderId; }
    public void setOrderId(UUID orderId) { this.orderId = orderId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public CustomerInfo getCustomer() { return customer; }
    public void setCustomer(CustomerInfo customer) { this.customer = customer; }
    public ProductInfo getProduct() { return product; }
    public void setProduct(ProductInfo product) { this.product = product; }
}
