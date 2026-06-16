package com.blogly.blogly.infrastructure.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.JWTVerifier
import com.blogly.blogly.application.auth.TokenProvider
import com.blogly.blogly.domain.exception.InvalidTokenException
import com.blogly.blogly.domain.user.User
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class JwtTokenProvider(private val jwtProperties: JwtProperties) : TokenProvider {

    private val algorithm: Algorithm = Algorithm.HMAC256(jwtProperties.secret)
    private val verifier: JWTVerifier = JWT.require(algorithm).build()

    override fun generateToken(user: User): String {
        val now = Instant.now()

        return JWT.create()
            .withSubject(user.email.value)
            .withIssuedAt(now)
            .withExpiresAt(now.plusMillis(jwtProperties.expirationMs))
            .sign(algorithm)
    }

    override fun extractUsername(token: String): String {
        val decoded = verifier.verify(token)
        return decoded.subject ?: throw InvalidTokenException("Token has no subject")
    }

    override fun isTokenValid(token: String, username: String): Boolean =
        try {
            extractUsername(token) == username
        } catch (_: JWTVerificationException) {
            false
        }
}
