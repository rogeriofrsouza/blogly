package com.blogly.blogly.domain.user

import com.blogly.blogly.domain.exception.DomainException

class InvalidEmailException(email: String) : DomainException("Invalid email format: $email")
