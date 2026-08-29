package com.blogly.blogly.infrastructure.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.JWTVerifier
import com.blogly.blogly.application.auth.TokenClaims
import com.blogly.blogly.application.auth.TokenProvider
import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.user.Email
import com.blogly.blogly.domain.user.Role
import com.blogly.blogly.domain.user.User
import com.blogly.blogly.domain.user.UserId
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
            .withClaim(ROLE_CLAIM, user.role.name)
            .withClaim(UID_CLAIM, TsidCodec.encode(user.id.value))
            .withIssuedAt(now)
            .withExpiresAt(now.plusMillis(jwtProperties.expirationMs))
            .sign(algorithm)
    }

    override fun parseToken(token: String): TokenClaims? {
        return try {
            val decoded = verifier.verify(token)

            val email = decoded.subject ?: return null
            val role = decoded.getClaim(ROLE_CLAIM).asString() ?: return null
            val userId = decoded.getClaim(UID_CLAIM).asString() ?: return null

            TokenClaims(
                email = Email(email),
                role = Role.valueOf(role),
                userId = UserId(TsidCodec.decode(userId))
            )
        } catch (_: JWTVerificationException) {
            null
        } catch (_: IllegalArgumentException) {
            null
        }
    }

    companion object {
        private const val ROLE_CLAIM = "role"
        private const val UID_CLAIM = "uid"
    }
}
