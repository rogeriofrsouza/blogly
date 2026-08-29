package com.blogly.blogly.infrastructure.security.jwt

import com.blogly.blogly.application.auth.TokenClaims
import com.blogly.blogly.application.auth.TokenProvider
import com.blogly.blogly.infrastructure.security.JwtPrincipal
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(private val tokenProvider: TokenProvider) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authHeader != null &&
            authHeader.startsWith(BEARER_PREFIX) &&
            SecurityContextHolder.getContext().authentication == null
        ) {
            tokenProvider.parseToken(authHeader.removePrefix(BEARER_PREFIX))
                ?.let(::setContext)
        }

        filterChain.doFilter(request, response)
    }

    private fun setContext(claims: TokenClaims) {
        val principal = JwtPrincipal(claims)
        val authorities = listOf(SimpleGrantedAuthority(claims.role.asAuthority()))

        val context = SecurityContextHolder.createEmptyContext()

        context.authentication =
            UsernamePasswordAuthenticationToken.authenticated(principal, null, authorities)

        SecurityContextHolder.setContext(context)
    }

    companion object {
        private const val BEARER_PREFIX = "Bearer "
    }
}
