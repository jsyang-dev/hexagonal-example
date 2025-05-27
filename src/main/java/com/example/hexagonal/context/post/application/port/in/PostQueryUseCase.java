package com.example.hexagonal.context.post.application.port.in;

import com.example.hexagonal.context.post.domain.model.Post;
import java.util.List;
import java.util.Optional;

public interface PostQueryUseCase {
    Optional<Post> getPost(Long id);
    List<Post> findAll();
} 