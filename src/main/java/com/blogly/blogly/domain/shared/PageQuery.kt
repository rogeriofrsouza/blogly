package com.blogly.blogly.domain.shared

data class PageQuery(val page: Int, val size: Int) {
    init {
        require(page >= 0) { "Page must not be negative" }
        require(size in 1..MAX_SIZE) { "Size must be between 1 and $MAX_SIZE" }
    }

    companion object {
        const val DEFAULT_SIZE = 10
        const val MAX_SIZE = 20
    }
}
