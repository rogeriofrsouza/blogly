package com.blogly.blogly.domain;

import java.util.Optional;

public interface ArticleRepository {

    Optional<Article> findById(ArticleId id);

    ArticleId save(Article article);
}
