package com.example.hexagonal.context.post.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PostTest {
    @Test
    @DisplayName("Post 생성 및 getter 동작 확인")
    void createAndGet() {
        Post post = new Post(1L, "제목", "내용", "작성자");
        assertThat(post.getId()).isEqualTo(1L);
        assertThat(post.getTitle()).isEqualTo("제목");
        assertThat(post.getContent()).isEqualTo("내용");
        assertThat(post.getAuthor()).isEqualTo("작성자");
    }

    @Test
    @DisplayName("동일 값이면 equals/hashCode도 동일")
    void equalsAndHashCode() {
        Post post1 = new Post(1L, "제목", "내용", "작성자");
        Post post2 = new Post(1L, "제목", "내용", "작성자");
        assertThat(post1).isEqualTo(post2);
        assertThat(post1.hashCode()).isEqualTo(post2.hashCode());
    }
}
