package com.example.hexagonal.context.post.acceptance;

import com.example.hexagonal.context.post.application.port.out.PostEventPublisherPort;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestEventConfig {
    @Bean
    @Primary
    public PostEventPublisherPort postEventPublisherPort() {
        return Mockito.mock(PostEventPublisherPort.class);
    }
} 