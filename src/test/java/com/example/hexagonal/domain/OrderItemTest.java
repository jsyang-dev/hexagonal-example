package com.example.hexagonal.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class OrderItemTest {

    @Test
    void createOrderItem_ShouldSetProductNameAndQuantity() {
        // given
        String productName = "상품1";
        int quantity = 2;

        // when
        OrderItem orderItem = new OrderItem(productName, quantity);

        // then
        assertThat(orderItem.getProductName()).isEqualTo(productName);
        assertThat(orderItem.getQuantity()).isEqualTo(quantity);
    }

    @Test
    void setProductName_ShouldUpdateProductName() {
        // given
        OrderItem orderItem = new OrderItem("상품1", 1);
        String newProductName = "상품2";

        // when
        orderItem.setProductName(newProductName);

        // then
        assertThat(orderItem.getProductName()).isEqualTo(newProductName);
    }

    @Test
    void setQuantity_ShouldUpdateQuantity() {
        // given
        OrderItem orderItem = new OrderItem("상품1", 1);
        int newQuantity = 3;

        // when
        orderItem.setQuantity(newQuantity);

        // then
        assertThat(orderItem.getQuantity()).isEqualTo(newQuantity);
    }
} 