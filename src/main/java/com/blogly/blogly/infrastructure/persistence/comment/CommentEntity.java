package com.blogly.blogly.infrastructure.persistence.comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    private Long id;

    @Column(nullable = false, length = 200)
    private String body;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public Long getId() {
        return this.id;
    }

    public String getBody() {
        return this.body;
    }

    public Long getPostId() {
        return this.postId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
