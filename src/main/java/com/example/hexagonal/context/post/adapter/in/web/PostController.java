package com.example.hexagonal.context.post.adapter.in.web;

import com.example.hexagonal.context.post.domain.model.Post;
import com.example.hexagonal.context.post.application.port.in.PostCommandUseCase;
import com.example.hexagonal.context.post.application.port.in.PostQueryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostCommandUseCase postCommandUseCase;
    private final PostQueryUseCase postQueryUseCase;

    public PostController(PostCommandUseCase postCommandUseCase, PostQueryUseCase postQueryUseCase) {
        this.postCommandUseCase = postCommandUseCase;
        this.postQueryUseCase = postQueryUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> registerPost(@RequestBody Post post) {
        postCommandUseCase.registerPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return postQueryUseCase.getPost(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postQueryUseCase.findAll());
    }
} 