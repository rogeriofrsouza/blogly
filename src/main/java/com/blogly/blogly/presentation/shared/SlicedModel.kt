package com.blogly.blogly.presentation.shared

data class SlicedModel<T>(
    val content: List<T>,
    val page: SliceMetadata
) {
    data class SliceMetadata(
        val size: Int,
        val number: Int,
        val hasNext: Boolean
    )
}
