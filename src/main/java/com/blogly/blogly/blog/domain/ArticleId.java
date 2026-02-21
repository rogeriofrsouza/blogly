package com.blogly.blogly.blog.domain;

public record ArticleId(Long value) {

    public ArticleId {
        if (value == null) {
            throw new IllegalArgumentException("ArticleId value cannot be null");
        }

        if (value < 0) {
            throw new IllegalArgumentException("ArticleId value cannot be negative");
        }
    }

    public static ArticleId from(Long value) {
        return new ArticleId(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
