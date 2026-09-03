package com.blogly.blogly.infrastructure.security

import com.blogly.blogly.application.auth.TokenClaims
import com.blogly.blogly.application.auth.UnauthenticatedException
import com.blogly.blogly.application.shared.UserProvider
import com.blogly.blogly.domain.user.Email
import com.blogly.blogly.domain.user.Role
import com.blogly.blogly.domain.user.UserId
import com.blogly.blogly.infrastructure.security.jwt.JwtPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityContextUserProvider : UserProvider {

    override fun currentUserId(): UserId = claims().userId

    override fun currentUserIdOrNull(): UserId? = claimsOrNull()?.userId

    override fun currentUserEmail(): Email = claims().email

    override fun currentUserRole(): Role = claims().role

    private fun claims(): TokenClaims =
        claimsOrNull() ?: throw UnauthenticatedException()

    private fun claimsOrNull(): TokenClaims? =
        (SecurityContextHolder.getContext().authentication
            ?.principal as? JwtPrincipal)
            ?.claims
}
