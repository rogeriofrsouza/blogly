package com.blogly.blogly.application.dto;

import com.blogly.blogly.domain.Article;

public record ArticleDto(
        Long id,
        String title,
        String body,
        String summary
) {
    public static ArticleDto from(Article article) {
        return new ArticleDto(
                article.getId().value(),
                article.getTitle(),
                article.getBody(),
                article.getSummary()
        );
    }
}
