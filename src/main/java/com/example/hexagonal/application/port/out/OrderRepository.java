package com.example.hexagonal.application.port.out;

import com.example.hexagonal.domain.Order;
import com.example.hexagonal.domain.OrderItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderRepository {
    int insert(Order order);
    Order selectById(Long id);
    List<Order> selectAll();
    int update(Order order);
    int insertItems(@Param("orderId") Long orderId, @Param("items") List<OrderItem> items);
    int deleteItemsByOrderId(@Param("orderId") Long orderId);
    List<OrderItem> selectItemsByOrderId(@Param("orderId") Long orderId);
} 