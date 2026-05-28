package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.exception.DomainException

class TitleAlreadyExistsException(title: Title) : DomainException("There is already a post with title: ${title.value}")
