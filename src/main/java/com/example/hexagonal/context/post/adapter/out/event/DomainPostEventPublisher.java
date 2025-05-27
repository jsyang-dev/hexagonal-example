package com.example.hexagonal.context.post.adapter.out.event;

import com.example.hexagonal.context.post.domain.model.Post;
import com.example.hexagonal.context.post.application.port.out.PostEventPublisherPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.example.hexagonal.context.post.domain.event.PostCreatedEvent;

@Component
public class DomainPostEventPublisher implements PostEventPublisherPort {
    private final ApplicationEventPublisher eventPublisher;

    public DomainPostEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publishPostCreatedEvent(Post post) {
        eventPublisher.publishEvent(new PostCreatedEvent(post));
    }
} 