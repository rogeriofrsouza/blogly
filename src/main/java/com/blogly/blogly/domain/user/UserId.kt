package com.blogly.blogly.domain.user

@JvmInline
value class UserId(val value: Long) {
    init {
        require(value > 0) { "UserId value must be positive" }
    }
}
