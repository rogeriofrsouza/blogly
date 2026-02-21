package com.blogly.blogly.blog.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {
}
