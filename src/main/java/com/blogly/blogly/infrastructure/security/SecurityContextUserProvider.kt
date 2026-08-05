package com.blogly.blogly.infrastructure.security

import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.user.User
import com.blogly.blogly.infrastructure.security.userdetails.SecurityUser
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityContextUserProvider : UserProvider {

    override fun currentUser(): User {
        val principal = SecurityContextHolder.getContext().authentication?.principal

        require(principal is SecurityUser) { "No authenticated user in the security context" }

        return principal.user
    }
}
