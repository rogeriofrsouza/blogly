package com.blogly.blogly.domain.user

import io.hypersistence.tsid.TSID

data class UserId(val value: Long) {
    init {
        require(value > 0) { "UserId value must be positive" }
    }

    companion object {
        fun generate() = UserId(TSID.fast().toLong())
    }
}
