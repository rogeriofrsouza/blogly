package com.blogly.blogly.infrastructure.persistence

import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.blogly.blogly.infrastructure.persistence"])
@EnableJdbcRepositories(basePackages = ["com.blogly.blogly.domain"])
class PersistenceConfiguration
