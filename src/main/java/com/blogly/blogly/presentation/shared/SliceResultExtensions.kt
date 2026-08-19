package com.blogly.blogly.presentation.shared

import com.blogly.blogly.domain.shared.SliceResult

fun <T : Any> SliceResult<T>.toSlicedModel(): SlicedModel<T> =
    SlicedModel(content, SlicedModel.SliceMetadata(size, page, hasNext))
