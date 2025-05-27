package com.example.hexagonal.context.post.acceptance;

import com.example.hexagonal.context.post.application.port.out.PostMessageSenderPort;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestKafkaConfig {
    @Bean
    @Primary
    public PostMessageSenderPort postMessageSenderPort() {
        return Mockito.mock(PostMessageSenderPort.class);
    }
} 