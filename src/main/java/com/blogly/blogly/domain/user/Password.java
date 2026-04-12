package com.blogly.blogly.domain.user;

import com.blogly.blogly.domain.exception.InvalidPasswordException;

import java.util.Objects;

public record Password(String hashedValue) {

    public static Password create(String plainText, PasswordHasher hasher) {
        Objects.requireNonNull(hasher, "Hasher cannot be null");

        if (plainText == null || plainText.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters");
        }

        return new Password(hasher.hash(plainText));
    }

    public boolean matches(String plainText, PasswordHasher hasher) {
        return hasher.matches(plainText, this.hashedValue);
    }
}
