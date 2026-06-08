package com.blogly.blogly.infrastructure.security.userdetails;

import com.blogly.blogly.domain.user.Email;
import com.blogly.blogly.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(repository.findByEmail(new Email(username)))
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
