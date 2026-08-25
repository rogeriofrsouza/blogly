package com.blogly.blogly.domain.post.exception

import com.blogly.blogly.domain.exception.NotOwnedException
import com.blogly.blogly.domain.post.Post
import com.blogly.blogly.domain.post.PostId

class PostNotOwnedException(id: PostId) : NotOwnedException(Post::class.java, id.value)
