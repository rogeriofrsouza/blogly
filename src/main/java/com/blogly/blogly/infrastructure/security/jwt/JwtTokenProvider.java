package com.blogly.blogly.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.blogly.blogly.application.auth.TokenProvider;
import com.blogly.blogly.domain.user.User;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
class JwtTokenProvider implements TokenProvider {

    private final JwtProperties jwtProperties;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.algorithm = Algorithm.HMAC256(jwtProperties.secret());
        this.verifier = JWT.require(algorithm).build();
    }

    @Override
    public String generateToken(User user) {
        Instant now = Instant.now();

        return JWT.create()
                .withSubject(user.getEmail().getValue())
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(jwtProperties.expirationMs()))
                .sign(algorithm);
    }

    @Override
    public String extractUsername(String token) {
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    @Override
    public boolean isTokenValid(String token, String username) {
        try {
            DecodedJWT decoded = verifier.verify(token);
            return decoded.getSubject().equals(username);
        } catch (JWTVerificationException _) {
            return false;
        }
    }
}
