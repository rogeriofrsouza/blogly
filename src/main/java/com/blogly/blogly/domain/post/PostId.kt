package com.blogly.blogly.domain.post

data class PostId(val value: Long) {
    init {
        require(value >= 1L) { "PostId value must be positive" }
    }
}
