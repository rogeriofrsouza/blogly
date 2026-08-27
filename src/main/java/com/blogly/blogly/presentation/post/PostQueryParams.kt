package com.blogly.blogly.presentation.post

import com.blogly.blogly.domain.post.PostQuery
import com.blogly.blogly.domain.post.PostStatus
import jakarta.validation.constraints.Size

data class PostQueryParams(
    @field:Size(max = 50)
    val title: String? = null,

    @field:Size(max = 50)
    val content: String? = null,

    val status: PostStatus? = null
) {
    fun toQuery() = PostQuery(normalize(title), normalize(content), status)

    private fun normalize(term: String?) = term?.trim()?.takeIf(String::isNotBlank)
}
