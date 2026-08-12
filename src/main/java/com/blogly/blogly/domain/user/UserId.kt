package com.blogly.blogly.domain.user

import com.blogly.blogly.domain.shared.EntityId

@JvmInline
value class UserId(override val value: Long) : EntityId {
    init {
        require(value > 0) { "UserId value must be positive" }
    }
}
