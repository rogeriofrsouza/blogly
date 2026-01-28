package com.blogly.chronicles.blog.domain;

import java.util.Optional;

public interface ArticleRepository {

    Optional<Article> findById(ArticleId id);

    void save(Article article);
}
