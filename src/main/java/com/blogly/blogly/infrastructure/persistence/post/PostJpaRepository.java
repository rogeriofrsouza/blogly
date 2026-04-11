package com.blogly.blogly.infrastructure.persistence.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

    boolean existsByTitleIgnoreCase(String title);
}
