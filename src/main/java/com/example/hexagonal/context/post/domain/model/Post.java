package com.example.hexagonal.context.post.domain.model;

import java.util.Objects;

public class Post {
    private Long id;
    private final String title;
    private final String content;
    private final String author;

    public Post(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getAuthor() {
        return author;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(content, post.content) &&
                Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, author);
    }
} 