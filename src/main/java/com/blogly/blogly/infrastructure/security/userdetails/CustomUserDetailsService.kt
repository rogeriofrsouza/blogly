package com.blogly.blogly.infrastructure.security.userdetails

import com.blogly.blogly.domain.user.Email
import com.blogly.blogly.domain.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val repository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        repository.findByEmail(Email(username))
            ?.let { SecurityUser(it) }
            ?: throw UsernameNotFoundException("User not found: $username")
}
