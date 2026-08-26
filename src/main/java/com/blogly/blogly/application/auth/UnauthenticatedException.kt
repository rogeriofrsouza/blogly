package com.blogly.blogly.application.auth

import com.blogly.blogly.application.exception.ApplicationException

class UnauthenticatedException : ApplicationException("Authentication required")
