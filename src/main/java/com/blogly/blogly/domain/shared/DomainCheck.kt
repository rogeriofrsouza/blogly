package com.blogly.blogly.domain.shared

import com.blogly.blogly.domain.exception.DomainException

inline fun domainCheck(value: Boolean, exception: () -> DomainException) {
    if (!value) throw exception()
}
