package com.blogly.blogly.infrastructure.persistence.post;

import com.blogly.blogly.domain.post.PostStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post",
        uniqueConstraints = {
                @UniqueConstraint(name = "uc_post_title", columnNames = {"title"})
        })
public class PostEntity {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private PostStatus status;
}
