package com.example.hexagonal.application.service;

import com.example.hexagonal.application.port.in.CreateOrderUseCase;
import com.example.hexagonal.application.port.in.GetOrderUseCase;
import com.example.hexagonal.application.port.in.ListOrderUseCase;
import com.example.hexagonal.application.port.in.UpdateOrderUseCase;
import com.example.hexagonal.application.port.out.OrderRepository;
import com.example.hexagonal.domain.Order;
import com.example.hexagonal.domain.OrderItem;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService implements CreateOrderUseCase, GetOrderUseCase, ListOrderUseCase, UpdateOrderUseCase {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(List<OrderItem> items) {
        Order order = new Order();
        order.setItems(items);
        orderRepository.insert(order);
        orderRepository.insertItems(order.getId(), items);
        return order;
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.selectById(id);
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.selectAll();
    }

    @Override
    public Order updateOrder(Long id, List<OrderItem> items) {
        Order order = orderRepository.selectById(id);
        if (order != null) {
            order.setItems(items);
            orderRepository.update(order);
            return order;
        }
        return null;
    }
} 