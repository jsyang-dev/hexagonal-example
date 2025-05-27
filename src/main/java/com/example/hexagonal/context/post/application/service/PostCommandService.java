package com.example.hexagonal.context.post.application.service;

import com.example.hexagonal.context.post.domain.model.Post;
import com.example.hexagonal.context.post.adapter.out.persistence.PostMasterMapper;
import com.example.hexagonal.context.post.application.port.in.PostCommandUseCase;
import com.example.hexagonal.context.post.application.port.out.PostMessageSenderPort;
import org.springframework.stereotype.Service;

@Service
public class PostCommandService implements PostCommandUseCase {
    private final PostMasterMapper postMasterMapper;
    private final PostMessageSenderPort postMessageSenderPort;

    public PostCommandService(PostMasterMapper postMasterMapper, PostMessageSenderPort postMessageSenderPort) {
        this.postMasterMapper = postMasterMapper;
        this.postMessageSenderPort = postMessageSenderPort;
    }

    @Override
    public void registerPost(Post post) {
        postMasterMapper.insert(post);
        postMessageSenderPort.sendPostCreated(post);
    }
}
