package com.blogly.blogly.domain.shared

data class PageResult<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val totalElements: Long
) {
    fun <R> map(transform: (T) -> R): PageResult<R> =
        PageResult(content.map(transform), page, size, totalElements)
}
