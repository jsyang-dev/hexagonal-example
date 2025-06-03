package com.example.hexagonal.context.post.application.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.hexagonal.context.post.adapter.out.persistence.PostMasterMapper;
import com.example.hexagonal.context.post.application.port.out.PostEventPublisherPort;
import com.example.hexagonal.context.post.domain.model.Post;

class PostCommandServiceTest {
    private PostMasterMapper postMasterMapper;
    private PostEventPublisherPort postEventPublisherPort;
    private PostCommandService postCommandService;

    @BeforeEach
    void setUp() {
        postMasterMapper = mock(PostMasterMapper.class);
        postEventPublisherPort = mock(PostEventPublisherPort.class);
        postCommandService = new PostCommandService(postMasterMapper, postEventPublisherPort);
    }

    @Test
    void registerPost_이벤트_발행됨() {
        Post post = new Post(null, "제목", "내용", "홍길동");

        postCommandService.registerPost(post);

        verify(postMasterMapper, times(1)).insert(post);
        verify(postEventPublisherPort, times(1)).publishPostCreatedEvent(post);
    }
} 