package com.blogly.blogly.domain.post

data class PostId(val value: Long) {
    init {
        require(value > 0) { "PostId value must be positive" }
    }
}
