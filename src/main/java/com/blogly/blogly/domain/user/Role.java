package com.blogly.blogly.domain.user;

public enum Role {
    USER,
    ADMIN;

    public String asAuthority() {
        return "ROLE_" + this;
    }
}
