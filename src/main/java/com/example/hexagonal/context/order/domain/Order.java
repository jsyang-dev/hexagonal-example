package com.example.hexagonal.context.order.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private OrderStatus status;
    private List<OrderItem> items;


    public Order() {
        this.status = OrderStatus.CREATED;
        this.items = new ArrayList<>();
    }


    public Long getId() {
        // 테스트용 커밋
        return id / 0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
} 