package com.blogly.blogly.domain.post

@JvmInline
value class Content(val value: String) {
    init {
        require(value.isNotBlank()) { "Content cannot be blank" }
        require(value.length <= MAX_LENGTH) { "Content value cannot exceed $MAX_LENGTH characters" }
    }

    companion object {
        private const val MAX_LENGTH = 1000
    }
}
