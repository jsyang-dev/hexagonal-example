package com.example.hexagonal.context.post.adapter.out.producer;

import com.example.hexagonal.context.post.domain.model.Post;
import com.example.hexagonal.context.post.application.port.out.PostMessageSenderPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPostMessageSender implements PostMessageSenderPort {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "post-created";

    public KafkaPostMessageSender(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendPostCreated(Post post) {
        kafkaTemplate.send(TOPIC, post.getId().toString(), post);
    }
} 