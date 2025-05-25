package com.example.hexagonal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.hexagonal.application.port.out")
public class HexagonalApplication {
    public static void main(String[] args) {
        SpringApplication.run(HexagonalApplication.class, args);
    }
} 