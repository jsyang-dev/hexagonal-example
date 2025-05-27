package com.example.hexagonal.context.post.adapter.in.event;

import com.example.hexagonal.context.post.domain.event.PostCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;

@Component
public class PostEventListener {
    private static final Logger log = LoggerFactory.getLogger(PostEventListener.class);

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(PostCreatedEvent event) {
        log.info("[도메인 이벤트] 게시글 생성됨: {}", event.getPost());
    }
} 