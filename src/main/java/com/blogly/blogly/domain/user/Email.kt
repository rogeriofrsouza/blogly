package com.blogly.blogly.domain.user

@JvmInline
value class Email(val value: String) {
    init {
        require(EMAIL_PATTERN.matches(value)) { "Invalid email format: $value" }
    }

    companion object {
        private val EMAIL_PATTERN = Regex("[A-Za-z0-9+_.-]+@(.+)")
    }
}
