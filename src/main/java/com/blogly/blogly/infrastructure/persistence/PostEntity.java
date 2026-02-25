package com.blogly.blogly.infrastructure.persistence;

import com.blogly.blogly.domain.PostStatus;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private PostStatus status;
}
