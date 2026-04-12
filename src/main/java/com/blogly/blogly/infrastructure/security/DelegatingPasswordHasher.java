package com.blogly.blogly.infrastructure.security;

import com.blogly.blogly.domain.user.PasswordHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DelegatingPasswordHasher implements PasswordHasher {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String hash(String plainText) {
        return passwordEncoder.encode(plainText);
    }

    @Override
    public boolean matches(String plainText, String hashedValue) {
        return passwordEncoder.matches(plainText, hashedValue);
    }
}
