package com.blogly.blogly.domain.post

@JvmInline
value class Title(val value: String) {
    init {
        require(value.isNotBlank()) { "Title cannot be blank" }
        require(value.length <= MAX_LENGTH) { "Title cannot exceed $MAX_LENGTH characters" }
    }

    companion object {
        private const val MAX_LENGTH = 255
    }
}
