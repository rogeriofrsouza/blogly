package com.blogly.blogly.domain.exception;

import com.blogly.blogly.domain.ArticleId;

public class ArticleNotFoundException extends DomainException {

    public ArticleNotFoundException(ArticleId id) {
        super("Article not found for id: " + id);
    }
}
