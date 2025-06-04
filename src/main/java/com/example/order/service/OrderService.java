package com.example.order.service;

import com.example.order.dto.OrderDTO;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ExternalServiceValidator validator;

    public Order createOrder(OrderDTO dto) {
        validator.validateCustomerId(dto.getCustomerId());
        validator.validateProductId(dto.getProductId());
        Order order = new Order(
            dto.getCustomerId(),
            dto.getProductId(),
            dto.getQuantity(),
            "CREATED"
        );
        return repository.save(order);
    }

    public Order findById(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order não encontrada: " + id));
    }
}
