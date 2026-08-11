package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.shared.EntityId

@JvmInline
value class PostId(override val value: Long) : EntityId {
    init {
        require(value > 0) { "PostId value must be positive" }
    }
}
