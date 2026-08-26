package com.blogly.blogly.infrastructure.security

import com.blogly.blogly.application.auth.UnauthenticatedException
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.user.User
import com.blogly.blogly.infrastructure.security.userdetails.SecurityUser
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityContextUserProvider : UserProvider {

    override fun currentUser(): User = currentUserOrNull() ?: throw UnauthenticatedException()

    override fun currentUserOrNull(): User? =
        SecurityContextHolder.getContext().authentication
            ?.principal
            ?.let { it as? SecurityUser }
            ?.user
}
