package com.blogly.blogly.domain.shared

data class PageQuery(
    val page: Int = DEFAULT_PAGE,
    val size: Int = DEFAULT_SIZE
) {
    init {
        require(page >= 0) { "Page number cannot be negative" }
        require(size in 1..MAX_SIZE) { "Page size must be between 1 and $MAX_SIZE" }
    }

    companion object {
        const val DEFAULT_PAGE = 0
        const val DEFAULT_SIZE = 10
        const val MAX_SIZE = 20
    }
}
