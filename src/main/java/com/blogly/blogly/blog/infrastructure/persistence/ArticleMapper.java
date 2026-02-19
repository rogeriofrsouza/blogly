package com.blogly.blogly.blog.infrastructure.persistence;

import com.blogly.blogly.blog.domain.Article;
import com.blogly.blogly.blog.domain.ArticleId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ArticleMapper {

    public ArticleEntity toEntity(Article article) {
        if (article == null) {
            return null;
        }

        ArticleEntity entity = new ArticleEntity();

        Optional.ofNullable(article.getId())
                .map(ArticleId::value)
                .ifPresent(entity::setId);

        entity.setTitle(article.getTitle());
        entity.setBody(article.getBody());
        entity.setSummary(article.getSummary());

        return entity;
    }

    public Article toDomain(ArticleEntity entity) {
        if (entity == null) {
            return null;
        }

        ArticleId id = entity.getId() != null ? ArticleId.from(entity.getId()) : null;

        return new Article(id, entity.getTitle(), entity.getBody(), entity.getSummary());
    }
}
