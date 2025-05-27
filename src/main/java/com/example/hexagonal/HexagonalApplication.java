package com.example.hexagonal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({
    "com.example.hexagonal.context.order.adapter.out.persistence",
    "com.example.hexagonal.context.post.adapter.out.persistence"
})
public class HexagonalApplication {
    public static void main(String[] args) {
        SpringApplication.run(HexagonalApplication.class, args);
    }
} 