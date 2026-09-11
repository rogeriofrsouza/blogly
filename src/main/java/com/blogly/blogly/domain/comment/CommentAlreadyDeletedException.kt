package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.exception.DomainException

class CommentAlreadyDeletedException(id: CommentId) : DomainException("Comment ${id.value} has already been deleted")
