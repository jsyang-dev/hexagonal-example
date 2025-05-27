package com.example.hexagonal.context.post.domain.event;

import com.example.hexagonal.context.post.domain.model.Post;
import lombok.Getter;

@Getter
public class PostCreatedEvent {
    private final Post post;

    public PostCreatedEvent(Post post) {
        this.post = post;
    }
} 