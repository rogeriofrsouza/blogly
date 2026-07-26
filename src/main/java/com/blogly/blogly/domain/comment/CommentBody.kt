package com.blogly.blogly.domain.comment

data class CommentBody(val value: String) {
    init {
        require(value.isNotBlank()) { "CommentBody cannot be blank" }
        require(value.length <= MAX_LENGTH) { "CommentBody value cannot exceed $MAX_LENGTH characters" }
    }

    companion object {
        private const val MAX_LENGTH = 200
    }
}
