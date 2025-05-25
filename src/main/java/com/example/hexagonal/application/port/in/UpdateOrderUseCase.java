package com.example.hexagonal.application.port.in;

import com.example.hexagonal.domain.Order;
import com.example.hexagonal.domain.OrderItem;
import java.util.List;

public interface UpdateOrderUseCase {
    Order updateOrder(Long id, List<OrderItem> items);
} 