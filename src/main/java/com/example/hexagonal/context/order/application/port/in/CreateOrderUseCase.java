package com.example.hexagonal.context.order.application.port.in;

import com.example.hexagonal.context.order.domain.Order;
import com.example.hexagonal.context.order.domain.OrderItem;
import java.util.List;

public interface CreateOrderUseCase {
    Order createOrder(List<OrderItem> items);
} 