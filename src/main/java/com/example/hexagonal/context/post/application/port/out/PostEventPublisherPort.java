package com.example.hexagonal.context.post.application.port.out;

import com.example.hexagonal.context.post.domain.model.Post;

public interface PostEventPublisherPort {
    void publishPostCreatedEvent(Post post);
} 