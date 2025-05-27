package com.example.hexagonal.context.post.application.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.hexagonal.context.post.adapter.out.persistence.PostMasterMapper;
import com.example.hexagonal.context.post.application.port.out.PostMessageSenderPort;
import com.example.hexagonal.context.post.domain.model.Post;

class PostCommandServiceTest {
    private PostMasterMapper postMasterMapper;
    private PostMessageSenderPort postMessageSenderPort;
    private PostCommandService postCommandService;

    @BeforeEach
    void setUp() {
        postMasterMapper = mock(PostMasterMapper.class);
        postMessageSenderPort = mock(PostMessageSenderPort.class);
        postCommandService = new PostCommandService(postMasterMapper, postMessageSenderPort);
    }

    @Test
    void registerPost_메시지_발행됨() {
        Post post = new Post(null, "제목", "내용", "홍길동");

        postCommandService.registerPost(post);

        verify(postMasterMapper, times(1)).insert(post);
        verify(postMessageSenderPort, times(1)).sendPostCreated(post);
    }
} 