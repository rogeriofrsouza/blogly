package com.blogly.blogly.domain.comment

@JvmInline
value class CommentId(val value: Long) {
    init {
        require(value > 0) { "CommentId value must be positive" }
    }
}
