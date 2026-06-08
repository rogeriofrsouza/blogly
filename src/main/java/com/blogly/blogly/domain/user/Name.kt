package com.blogly.blogly.domain.user

data class Name(val value: String) {
    init {
        if (value.length !in 3..50) {
            throw InvalidNameException("Name must be between 3 and 50 characters")
        }
    }
}
