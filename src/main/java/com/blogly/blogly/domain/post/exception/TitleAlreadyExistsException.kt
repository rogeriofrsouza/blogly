package com.blogly.blogly.domain.post.exception

import com.blogly.blogly.domain.exception.DomainException
import com.blogly.blogly.domain.post.Title

class TitleAlreadyExistsException(title: Title) : DomainException("There is already a post with title: ${title.value}")
