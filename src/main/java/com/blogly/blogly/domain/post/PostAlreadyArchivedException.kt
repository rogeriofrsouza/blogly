package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.exception.DomainException

class PostAlreadyArchivedException(id: PostId) : DomainException("Post ${id.value} has already been archived")
