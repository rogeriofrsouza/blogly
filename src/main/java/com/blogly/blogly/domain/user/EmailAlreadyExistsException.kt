package com.blogly.blogly.domain.user

import com.blogly.blogly.domain.exception.DomainException

class EmailAlreadyExistsException(email: String) : DomainException("Email already registered: $email")
