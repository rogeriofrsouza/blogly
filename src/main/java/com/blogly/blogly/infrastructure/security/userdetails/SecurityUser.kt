package com.blogly.blogly.infrastructure.security.userdetails

import com.blogly.blogly.domain.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(val user: User) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> =
        listOf(
            SimpleGrantedAuthority(user.role.asAuthority())
        )

    override fun getPassword(): String = user.password.hashedValue

    override fun getUsername(): String = user.email.value
}
