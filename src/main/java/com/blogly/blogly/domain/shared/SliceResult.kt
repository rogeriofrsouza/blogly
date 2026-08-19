package com.blogly.blogly.domain.shared

data class SliceResult<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val hasNext: Boolean
) {
    fun <R> map(transform: (T) -> R): SliceResult<R> =
        SliceResult(content.map(transform), page, size, hasNext)
}
