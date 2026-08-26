package com.blogly.blogly.domain.user

@JvmInline
value class Bio(val value: String) {
    init {
        require(value.isNotBlank()) { "Bio must not be blank" }
        require(value.length <= MAX_LENGTH) { "Bio must be at most $MAX_LENGTH characters" }
    }

    companion object {
        private const val MAX_LENGTH = 200
    }
}
