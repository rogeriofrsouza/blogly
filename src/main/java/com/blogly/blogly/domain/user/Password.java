package com.blogly.blogly.domain.user;

import com.blogly.blogly.domain.exception.InvalidPasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

public record Password(String hashedValue) {

    public static Password create(String plainText, PasswordEncoder encoder) {
        Objects.requireNonNull(encoder, "Encoder cannot be null");

        if (plainText == null || plainText.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters");
        }

        return new Password(encoder.encode(plainText));
    }

    public boolean matches(String plainText, PasswordEncoder encoder) {
        return encoder.matches(plainText, this.hashedValue);
    }
}
