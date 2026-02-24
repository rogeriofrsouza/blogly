package com.blogly.blogly.domain;

public record Title(String value) {

    private static final int MAX_LENGTH = 255;

    public Title {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Title value cannot be null or blank");
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Title value cannot exceed " + MAX_LENGTH + " characters");
        }
    }
}
