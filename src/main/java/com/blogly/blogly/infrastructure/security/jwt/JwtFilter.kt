package com.blogly.blogly.infrastructure.security.jwt

import com.blogly.blogly.application.auth.TokenProvider
import com.blogly.blogly.infrastructure.security.userdetails.CustomUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val tokenProvider: TokenProvider,
    private val userDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            val token = authHeader.removePrefix(BEARER_PREFIX)
            val username = tokenProvider.extractUsername(token)

            loadUserAndSetContext(username, token)
        }

        filterChain.doFilter(request, response)
    }

    private fun loadUserAndSetContext(username: String, token: String) {
        if (SecurityContextHolder.getContext().authentication != null) {
            return
        }

        val userDetails = userDetailsService.loadUserByUsername(username)

        if (tokenProvider.isTokenValid(token, userDetails.username)) {
            val context = SecurityContextHolder.createEmptyContext()

            context.authentication =
                UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.authorities)

            SecurityContextHolder.setContext(context)
        }
    }

    companion object {
        private const val BEARER_PREFIX = "Bearer "
    }
}
