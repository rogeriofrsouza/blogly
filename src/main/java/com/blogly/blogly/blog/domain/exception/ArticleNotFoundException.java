package com.blogly.blogly.blog.domain.exception;

import com.blogly.blogly.blog.domain.ArticleId;

public class ArticleNotFoundException extends DomainException {

    public ArticleNotFoundException(ArticleId id) {
        super("Article not found for id: " + id);
    }
}
