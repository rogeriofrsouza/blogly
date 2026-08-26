package com.blogly.blogly.domain.user

@JvmInline
value class AvatarKey(val value: String) {
    init {
        require(value.length <= MAX_LENGTH) { "Avatar key must be at most $MAX_LENGTH characters" }
        require(KEY_PATTERN.matches(value)) { "Invalid avatar key format: $value" }
    }

    companion object {
        private const val MAX_LENGTH = 100
        private val KEY_PATTERN = Regex("avatars/[0-9A-Za-z]+\\.(png|jpg|jpeg|webp)")
    }
}
