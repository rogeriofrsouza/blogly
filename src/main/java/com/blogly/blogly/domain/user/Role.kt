package com.blogly.blogly.domain.user

enum class Role {
    USER,
    ADMIN;

    fun asAuthority() = "ROLE_$this"
}
