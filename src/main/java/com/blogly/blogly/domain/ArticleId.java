package com.blogly.blogly.domain;

import java.util.Objects;

public record ArticleId(Long value) {

    public ArticleId {
        Objects.requireNonNull(value, "ArticleId value cannot be null");

        if (value < 1) {
            throw new IllegalArgumentException("ArticleId value must be positive");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
