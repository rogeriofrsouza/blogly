package com.blogly.blogly.domain;

import java.util.Objects;

public record PostId(Long value) {

    public PostId {
        Objects.requireNonNull(value, "PostId value cannot be null");

        if (value < 1) {
            throw new IllegalArgumentException("PostId value must be positive");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
