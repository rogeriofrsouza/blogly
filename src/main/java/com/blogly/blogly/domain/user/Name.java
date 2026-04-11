package com.blogly.blogly.domain.user;

import com.blogly.blogly.domain.exception.InvalidNameException;

import java.util.Objects;

public record Name(String value) {

    public Name {
        Objects.requireNonNull(value, "Name value cannot be null");

        if (value.length() < 3 || value.length() > 50) {
            throw new InvalidNameException("Name must be between 3 and 50 characters");
        }
    }
}
