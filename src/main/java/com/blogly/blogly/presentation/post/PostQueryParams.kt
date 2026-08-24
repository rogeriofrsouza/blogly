package com.blogly.blogly.presentation.post

import com.blogly.blogly.domain.post.PostQuery
import com.blogly.blogly.domain.post.PostStatus
import org.hibernate.validator.constraints.Length

data class PostQueryParams(
    @field:Length(max = 50)
    val title: String? = null,

    @field:Length(max = 50)
    val content: String? = null,

    val status: PostStatus = PostStatus.PUBLISHED
) {
    fun toQuery() = PostQuery(normalize(title), normalize(content), status)

    private fun normalize(term: String?) = term?.trim()?.takeIf(String::isNotBlank)
}
