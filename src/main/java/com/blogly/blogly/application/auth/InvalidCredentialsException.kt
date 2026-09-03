package com.blogly.blogly.application.auth

import com.blogly.blogly.application.exception.ApplicationException

class InvalidCredentialsException : ApplicationException("Invalid credentials")
