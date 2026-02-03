package com.blogly.blogly.blog.domain;

import java.util.Optional;

public interface ArticleRepository {

    Optional<Article> findById(ArticleId id);

    ArticleId save(Article article);
}
