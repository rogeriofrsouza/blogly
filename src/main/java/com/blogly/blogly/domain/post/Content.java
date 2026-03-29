package com.blogly.blogly.domain.post;

public record Content(String value) {

    private static final int MAX_LENGTH = 1_000;

    public Content {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Content value cannot be null or blank");
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Content value cannot exceed " + MAX_LENGTH + " characters");
        }
    }
}
