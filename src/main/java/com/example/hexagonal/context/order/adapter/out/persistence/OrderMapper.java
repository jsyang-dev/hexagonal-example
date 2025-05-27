package com.example.hexagonal.context.order.adapter.out.persistence;

import com.example.hexagonal.context.order.domain.Order;
import com.example.hexagonal.context.order.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrderMapper {
    int insert(Order order);
    Order selectById(Long id);
    List<Order> selectAll();
    int update(Order order);
    int insertItems(@Param("orderId") Long orderId, @Param("items") List<OrderItem> items);
    int deleteItemsByOrderId(@Param("orderId") Long orderId);
    List<OrderItem> selectItemsByOrderId(@Param("orderId") Long orderId);
} 