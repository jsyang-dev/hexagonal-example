package com.example.hexagonal.application.port.in;

import com.example.hexagonal.domain.Order;

public interface GetOrderUseCase {
    Order getOrder(Long id);
} 