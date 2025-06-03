package com.example.hexagonal.context.post.application.service;

import com.example.hexagonal.context.post.domain.model.Post;
import com.example.hexagonal.context.post.adapter.out.persistenc.PostMasterMapper;
import com.example.hexagonal.context.post.application.port.in.PostQueryUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostQueryService implements PostQueryUseCase {
    private final PostMasterMapper postMasterMapper;

    public PostQueryService(PostMasterMapper postMasterMapper) {
        this.postMasterMapper = postMasterMapper;
    }

    @Override
    public Optional<Post> getPost(Long id) {
        return postMasterMapper.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postMasterMapper.findAll();
    }
}
