package com.example.hexagonal.context.order.application.service;

import com.example.hexagonal.context.order.application.port.in.CreateOrderUseCase;
import com.example.hexagonal.context.order.application.port.in.GetOrderUseCase;
import com.example.hexagonal.context.order.application.port.in.ListOrderUseCase;
import com.example.hexagonal.context.order.application.port.in.UpdateOrderUseCase;
import com.example.hexagonal.context.order.adapter.out.persistence.OrderMapper;
import com.example.hexagonal.context.order.domain.Order;
import com.example.hexagonal.context.order.domain.OrderItem;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService implements CreateOrderUseCase, GetOrderUseCase, ListOrderUseCase, UpdateOrderUseCase {
    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public Order createOrder(List<OrderItem> items) {
        Order order = new Order();
        order.setItems(items);
        orderMapper.insert(order);
        orderMapper.insertItems(order.getId(), items);
        return order;
    }

    @Override
    public Order getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<Order> listOrders() {
        return orderMapper.selectAll();
    }

    @Override
    public Order updateOrder(Long id, List<OrderItem> items) {
        Order order = orderMapper.selectById(id);
        if (order != null) {
            order.setItems(items);
            orderMapper.update(order);
            return order;
        }
        return null;
    }
} 