package com.blogly.blogly.application.user

import com.blogly.blogly.application.exception.ApplicationException

class UserAlreadyAdminException : ApplicationException("User is already an admin")
