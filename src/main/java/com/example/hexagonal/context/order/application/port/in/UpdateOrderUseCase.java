package com.example.hexagonal.context.order.application.port.in;

import com.example.hexagonal.context.order.domain.Order;
import com.example.hexagonal.context.order.domain.OrderItem;
import java.util.List;

public interface UpdateOrderUseCase {
    Order updateOrder(Long id, List<OrderItem> items);
} 