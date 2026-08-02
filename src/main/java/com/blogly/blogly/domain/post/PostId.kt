package com.blogly.blogly.domain.post

import io.hypersistence.tsid.TSID

data class PostId(val value: Long) {
    init {
        require(value >= 1L) { "PostId value must be positive" }
    }

    companion object {
        fun generate() = PostId(TSID.fast().toLong())
    }
}
