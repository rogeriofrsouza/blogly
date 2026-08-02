package com.blogly.blogly.domain.comment

import io.hypersistence.tsid.TSID

data class CommentId(val value: Long) {
    init {
        require(value > 0) { "CommentId value must be positive" }
    }

    companion object {
        fun generate() = CommentId(TSID.fast().toLong())
    }
}
