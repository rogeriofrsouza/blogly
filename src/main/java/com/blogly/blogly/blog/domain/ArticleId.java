package com.blogly.blogly.blog.domain;

import java.util.Objects;

public record ArticleId(Long value) {

    public ArticleId {
        Objects.requireNonNull(value, "ArticleId value cannot be null");

        if (value < 0) {
            throw new IllegalArgumentException("ArticleId value cannot be negative");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
