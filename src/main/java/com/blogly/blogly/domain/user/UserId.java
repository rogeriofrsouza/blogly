package com.blogly.blogly.domain.user;

import java.util.Objects;

public record UserId(Long value) {

    public UserId {
        Objects.requireNonNull(value, "UserId value cannot be null");

        if (value < 1) {
            throw new IllegalArgumentException("UserId value must be positive");
        }
    }
}
