package com.blogly.blogly.presentation.shared

import com.blogly.blogly.domain.shared.PageQuery

data class PageQueryDto(
    val page: Int = PageQuery.FIRST_PAGE,
    val size: Int = PageQuery.DEFAULT_SIZE,
) {
    fun toQuery() = PageQuery(page, size)
}
