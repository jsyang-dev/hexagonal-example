package com.example.hexagonal.context.post.application.service;

import com.example.hexagonal.context.post.domain.model.Post;
import com.example.hexagonal.context.post.adapter.out.persistence.PostMasterMapper;
import com.example.hexagonal.context.post.application.port.in.PostCommandUseCase;
import org.springframework.stereotype.Service;

@Service
public class PostCommandService implements PostCommandUseCase {
    private final PostMasterMapper postMasterMapper;

    public PostCommandService(PostMasterMapper postMasterMapper) {
        this.postMasterMapper = postMasterMapper;
    }

    @Override
    public void registerPost(Post post) {
        postMasterMapper.insert(post);
    }
}
