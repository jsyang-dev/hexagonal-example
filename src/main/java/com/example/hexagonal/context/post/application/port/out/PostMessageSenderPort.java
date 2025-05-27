package com.example.hexagonal.context.post.application.port.out;

import com.example.hexagonal.context.post.domain.model.Post;

public interface PostMessageSenderPort {
    void sendPostCreated(Post post);
} 