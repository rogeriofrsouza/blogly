package com.blogly.blogly.domain.comment

import com.blogly.blogly.domain.exception.NotOwnedException

class CommentNotOwnedException(id: CommentId) : NotOwnedException(Comment::class.java, id.value)
