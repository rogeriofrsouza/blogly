package com.blogly.blogly.infrastructure.persistence;

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

    @Column(length = 100)
    private String summary;
}
