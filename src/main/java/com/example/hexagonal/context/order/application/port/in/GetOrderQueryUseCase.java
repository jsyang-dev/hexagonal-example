package com.example.hexagonal.context.order.application.port.in;

import com.example.hexagonal.context.order.domain.Order;

public interface GetOrderQueryUseCase {
    Order getOrder(Long id);
} 