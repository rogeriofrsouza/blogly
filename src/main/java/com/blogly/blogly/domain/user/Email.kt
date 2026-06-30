package com.blogly.blogly.domain.user

import com.blogly.blogly.domain.user.exception.InvalidEmailException

data class Email(val value: String) {
    init {
        if (EMAIL_PATTERN.matches(value).not()) {
            throw InvalidEmailException(value)
        }
    }

    companion object {
        private val EMAIL_PATTERN = Regex("[A-Za-z0-9+_.-]+@(.+)")
    }
}
