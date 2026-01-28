package com.blogly.chronicles.article.domain;

public record ArticleId(Long value) {

    public ArticleId {
        if (value == null) {
            throw new IllegalArgumentException("ArticleId value cannot be null");
        }

        if (value < 0) {
            throw new IllegalArgumentException("ArticleId value cannot be negative");
        }
    }
}
