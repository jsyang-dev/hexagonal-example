package com.example.hexagonal.application.port.in;

import com.example.hexagonal.domain.Order;
import java.util.List;
import com.example.hexagonal.domain.OrderItem;

public interface CreateOrderUseCase {
    Order createOrder(List<OrderItem> items);
} 