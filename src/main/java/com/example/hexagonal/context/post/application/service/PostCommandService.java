package com.example.hexagonal.context.post.application.service;

import com.example.hexagonal.context.post.domain.model.Post;
import com.example.hexagonal.context.post.adapter.out.persistence.PostMasterMapper;
import com.example.hexagonal.context.post.application.port.in.PostCommandUseCase;
import com.example.hexagonal.context.post.application.port.out.PostEventPublisherPort;
import org.springframework.stereotype.Service;

@Service
public class PostCommandService implements PostCommandUseCase {
    private final PostMasterMapper postMasterMapper;
    private final PostEventPublisherPort postEventPublisherPort;

    public PostCommandService(PostMasterMapper postMasterMapper, PostEventPublisherPort postEventPublisherPort) {
        this.postMasterMapper = postMasterMapper;
        this.postEventPublisherPort = postEventPublisherPort;
    }

    @Override
    public void registerPost(Post post) {
        postMasterMapper.insert(post);
        postEventPublisherPort.publishPostCreatedEvent(post);
    }
}
