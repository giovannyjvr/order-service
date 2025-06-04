package com.example.order.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
public class ExternalServiceValidator {

    public final WebClient webClientProducts;
    public final WebClient webClientAccounts;

    public ExternalServiceValidator(
            @Value("${product.service.url}") String productServiceUrl,
            @Value("${account.service.url}") String accountServiceUrl) {
        this.webClientProducts = WebClient.builder()
                .baseUrl(productServiceUrl)
                .build();
        this.webClientAccounts = WebClient.builder()
                .baseUrl(accountServiceUrl)
                .build();
    }

    public void validateProductId(UUID productId) {
        ProductInfoResponse resp = webClientProducts.get()
                .uri("/products/{id}", productId)
                .retrieve()
                .bodyToMono(ProductInfoResponse.class)
                .block();
        if (resp == null) {
            throw new RuntimeException("Produto inválido: " + productId);
        }
    }

    public void validateCustomerId(UUID customerId) {
        CustomerInfoResponse resp = webClientAccounts.get()
                .uri("/accounts/{id}", customerId)
                .retrieve()
                .bodyToMono(CustomerInfoResponse.class)
                .block();
        if (resp == null) {
            throw new RuntimeException("Cliente inválido: " + customerId);
        }
    }

    public static class ProductInfoResponse {
        private UUID id;
        private String name;
        private Double price;
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }
    }

    public static class CustomerInfoResponse {
        private UUID id;
        private String name;
        private String email;
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
