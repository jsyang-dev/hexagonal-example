package com.example.hexagonal.context.order.application.port.in;

import com.example.hexagonal.context.order.domain.Order;
import java.util.List;

public interface ListOrderUseCase {
    List<Order> listOrders();
} 