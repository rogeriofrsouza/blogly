package com.blogly.blogly.infrastructure.security.jwt

import com.blogly.blogly.application.auth.TokenClaims
import org.springframework.security.core.AuthenticatedPrincipal

@JvmInline
value class JwtPrincipal(val claims: TokenClaims) : AuthenticatedPrincipal {

    override fun getName(): String = claims.email.value
}
