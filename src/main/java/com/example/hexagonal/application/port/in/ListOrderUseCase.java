package com.example.hexagonal.application.port.in;

import com.example.hexagonal.domain.Order;
import java.util.List;

public interface ListOrderUseCase {
    List<Order> listOrders();
} 