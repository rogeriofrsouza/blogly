package com.blogly.blogly.application.user

import com.blogly.blogly.application.exception.ApplicationException

class AdminPrivilegeRequiredException : ApplicationException("This operation requires admin privileges")
