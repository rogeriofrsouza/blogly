package com.blogly.blogly.infrastructure.security

import com.blogly.blogly.domain.user.PasswordHasher
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DelegatingPasswordHasher(private val passwordEncoder: PasswordEncoder) : PasswordHasher {

    override fun hash(plainText: String) = passwordEncoder.encode(plainText)!!

    override fun matches(plainText: String, hashedValue: String) =
        passwordEncoder.matches(plainText, hashedValue)
}
