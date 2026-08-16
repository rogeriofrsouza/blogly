package com.blogly.blogly.presentation.shared

import com.blogly.blogly.domain.shared.PageResult
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PagedModel

fun <T : Any> PageResult<T>.toPagedModel(): PagedModel<T> =
    PagedModel(PageImpl(content, PageRequest.of(page, size), totalElements))
