package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.exception.DomainException

class PostAlreadyDeletedException(id: PostId) : DomainException("Post ${id.value} has already been deleted")
