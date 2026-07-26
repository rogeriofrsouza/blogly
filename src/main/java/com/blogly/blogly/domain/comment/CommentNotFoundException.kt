package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.exception.NotFoundException

class CommentNotFoundException(id: CommentId) : NotFoundException(Comment::class.java, id.value)
