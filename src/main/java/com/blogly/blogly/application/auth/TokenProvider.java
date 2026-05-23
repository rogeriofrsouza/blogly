package com.blogly.blogly.application.auth;

import com.blogly.blogly.domain.user.User;

public interface TokenProvider {

    String generateToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, String username);
}
