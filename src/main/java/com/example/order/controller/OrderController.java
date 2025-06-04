package com.example.order.controller;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderResponseDTO;
import com.example.order.model.Order;
import com.example.order.service.OrderService;
import com.example.order.service.ExternalServiceValidator;
import com.example.order.service.ExternalServiceValidator.ProductInfoResponse;
import com.example.order.service.ExternalServiceValidator.CustomerInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private ExternalServiceValidator validator;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @RequestBody OrderDTO dto,
            @RequestHeader("Authorization") String authHeader) {

        Order order = service.createOrder(dto);

        ProductInfoResponse prod = 
            validator.webClientProducts.get()
                .uri("/products/{id}", dto.getProductId())
                .retrieve()
                .bodyToMono(ProductInfoResponse.class)
                .block();

        CustomerInfoResponse cust = 
            validator.webClientAccounts.get()
                .uri("/accounts/{id}", dto.getCustomerId())
                .retrieve()
                .bodyToMono(CustomerInfoResponse.class)
                .block();

        OrderResponseDTO response = new OrderResponseDTO(
            order.getId(),
            order.getQuantity(),
            order.getStatus(),
            new OrderResponseDTO.CustomerInfo(order.getCustomerId(), cust.getName(), cust.getEmail()),
            new OrderResponseDTO.ProductInfo(order.getProductId(), prod.getName(), prod.getPrice())
        );

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(  
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authHeader) {

        Order order = service.findById(id);

        ProductInfoResponse prod = 
            validator.webClientProducts.get()
                .uri("/products/{id}", order.getProductId())
                .retrieve()
                .bodyToMono(ProductInfoResponse.class)
                .block();

        CustomerInfoResponse cust = 
            validator.webClientAccounts.get()
                .uri("/accounts/{id}", order.getCustomerId())
                .retrieve()
                .bodyToMono(CustomerInfoResponse.class)
                .block();

        OrderResponseDTO response = new OrderResponseDTO(
            order.getId(),
            order.getQuantity(),
            order.getStatus(),
            new OrderResponseDTO.CustomerInfo(order.getCustomerId(), cust.getName(), cust.getEmail()),
            new OrderResponseDTO.ProductInfo(order.getProductId(), prod.getName(), prod.getPrice())
        );

        return ResponseEntity.ok(response);
    }
}
