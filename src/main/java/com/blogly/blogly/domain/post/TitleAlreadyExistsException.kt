package com.blogly.blogly.domain.post

import com.blogly.blogly.domain.exception.DomainException

class TitleAlreadyExistsException(title: String) : DomainException("There is already a post with title: $title")
