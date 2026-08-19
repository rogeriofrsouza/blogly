package com.blogly.blogly.presentation.shared

import com.blogly.blogly.domain.shared.PageQuery
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero

data class PageQueryParams(
    @field:PositiveOrZero
    val page: Int = PageQuery.FIRST_PAGE,
    @field:Positive
    val size: Int = PageQuery.DEFAULT_SIZE,
) {
    fun toQuery() = PageQuery(page, size)
}
