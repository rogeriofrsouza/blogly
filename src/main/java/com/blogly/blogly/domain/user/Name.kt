package com.blogly.blogly.domain.user

@JvmInline
value class Name(val value: String) {
    init {
        require(value.length in MIN_LENGTH..MAX_LENGTH) {
            "Name must be between $MIN_LENGTH and $MAX_LENGTH characters"
        }
    }

    companion object {
        private const val MIN_LENGTH = 3
        private const val MAX_LENGTH = 50
    }
}
