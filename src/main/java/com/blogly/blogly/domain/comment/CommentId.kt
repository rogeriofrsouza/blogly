package com.blogly.blogly.domain.comment

data class CommentId(val value: Long) {
    init {
        require(value > 0) { "CommentId value must be positive" }
    }
}
