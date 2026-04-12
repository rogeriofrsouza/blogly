package com.blogly.blogly.domain.user;

public interface PasswordHasher {

    String hash(String plainText);

    boolean matches(String plainText, String hashedValue);
}
