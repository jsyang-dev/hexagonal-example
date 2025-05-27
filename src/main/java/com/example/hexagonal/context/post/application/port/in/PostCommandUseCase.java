package com.example.hexagonal.context.post.application.port.in;

import com.example.hexagonal.context.post.domain.model.Post;

public interface PostCommandUseCase {
    void registerPost(Post post);
} 