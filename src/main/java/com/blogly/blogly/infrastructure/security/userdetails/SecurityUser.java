package com.blogly.blogly.infrastructure.security.userdetails;

import com.blogly.blogly.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class SecurityUser implements UserDetails {

    private final transient User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(user.getRole().asAuthority()));
    }

    @Override
    public String getPassword() {
        return user.getPassword().hashedValue();
    }

    @Override
    public String getUsername() {
        return user.getEmail().value();
    }
}
