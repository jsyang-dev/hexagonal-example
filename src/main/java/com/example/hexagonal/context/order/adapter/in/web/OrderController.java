package com.example.hexagonal.context.order.adapter.in.web;

import com.example.hexagonal.context.order.application.port.in.CreateOrderCommandUseCase;
import com.example.hexagonal.context.order.application.port.in.GetOrderQueryUsecase;
import com.example.hexagonal.context.order.application.port.in.ListOrderUseCase;
import com.example.hexagonal.context.order.application.port.in.UpdateOrderUseCase;
import com.example.hexagonal.context.order.domain.Order;
import com.example.hexagonal.context.order.domain.OrderItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final CreateOrderCommandUseCase createOrderUseCase;
    private final GetOrderQueryUsecase getOrderQueryUseCase;
    private final ListOrderUseCase listOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;

    public OrderController(
        CreateOrderCommandUseCase createOrderUseCase,
        GetOrderQueryUsecase getOrderQueryUseCase,
        ListOrderUseCase listOrderUseCase,
        UpdateOrderUseCase updateOrderUseCase
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderQueryUseCase = getOrderQueryUseCase;
        this.listOrderUseCase = listOrderUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
    }

    @PostMapping
    public Order createOrder(@RequestBody List<OrderItem> items) {
        return createOrderUseCase.createOrder(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = getOrderQueryUseCase.getOrder(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Order> listOrders() {
        return listOrderUseCase.listOrders();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody List<OrderItem> items) {
        Order updatedOrder = updateOrderUseCase.updateOrder(id, items);
        return updatedOrder != null ? ResponseEntity.ok(updatedOrder) : ResponseEntity.notFound().build();
    }
} 