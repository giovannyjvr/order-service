package com.example.order.dto;

import java.util.UUID;

public class OrderDTO {
    private UUID customerId;
    private UUID productId;
    private Integer quantity;

    public OrderDTO() {}

    public OrderDTO(UUID customerId, UUID productId, Integer quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
