package com.blogly.blogly.presentation.shared

import com.blogly.blogly.domain.shared.PageQuery
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.PositiveOrZero

data class PageQueryParams(
    @field:PositiveOrZero
    val page: Int = 0,

    @field:Min(1)
    @field:Max(PageQuery.MAX_SIZE.toLong())
    val size: Int = PageQuery.DEFAULT_SIZE
) {
    fun toQuery() = PageQuery(page, size)
}
