package com.blogly.blogly.infrastructure.bootstrap;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.admin")
public record AdminProperties(
        String email,
        String password,
        String name
) {
}
