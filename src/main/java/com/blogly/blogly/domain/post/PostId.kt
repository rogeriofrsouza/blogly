package com.blogly.blogly.domain.post

@JvmInline
value class PostId(val value: Long) {
    init {
        require(value > 0) { "PostId value must be positive" }
    }
}
