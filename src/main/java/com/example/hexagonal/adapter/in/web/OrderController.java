package com.example.hexagonal.adapter.in.web;

import com.example.hexagonal.application.port.in.CreateOrderUseCase;
import com.example.hexagonal.application.port.in.GetOrderUseCase;
import com.example.hexagonal.application.port.in.ListOrderUseCase;
import com.example.hexagonal.application.port.in.UpdateOrderUseCase;
import com.example.hexagonal.domain.Order;
import com.example.hexagonal.domain.OrderItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final ListOrderUseCase listOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;

    public OrderController(
        CreateOrderUseCase createOrderUseCase,
        GetOrderUseCase getOrderUseCase,
        ListOrderUseCase listOrderUseCase,
        UpdateOrderUseCase updateOrderUseCase
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.listOrderUseCase = listOrderUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
    }

    @PostMapping
    public Order createOrder(@RequestBody List<OrderItem> items) {
        return createOrderUseCase.createOrder(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = getOrderUseCase.getOrder(id);
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