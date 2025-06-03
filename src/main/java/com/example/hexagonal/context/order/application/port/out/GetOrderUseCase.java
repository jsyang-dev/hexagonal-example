package com.example.hexagonal.context.order.application.port.out;

import com.example.hexagonal.context.order.domain.Order;

public interface GetOrderUseCase {
    Order getOrder(Long id);
} 