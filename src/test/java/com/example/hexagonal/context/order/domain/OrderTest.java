package com.example.hexagonal.context.order.domain;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void createOrder_ShouldInitializeWithCreatedStatus() {
        // when
        Order order = new Order();

        // then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CREATED);
        assertThat(order.getItems()).isEmpty();
    }

    @Test
    void setItems_ShouldUpdateOrderItems() {
        // given
        Order order = new Order();
        List<OrderItem> items = List.of(
            new OrderItem("상품1", 1),
            new OrderItem("상품2", 2)
        );

        // when
        order.setItems(items);

        // then
        assertThat(order.getItems()).hasSize(2);
        assertThat(order.getItems().get(0).getProductName()).isEqualTo("상품1");
        assertThat(order.getItems().get(0).getQuantity()).isEqualTo(1);
        assertThat(order.getItems().get(1).getProductName()).isEqualTo("상품2");
        assertThat(order.getItems().get(1).getQuantity()).isEqualTo(2);
    }
} 