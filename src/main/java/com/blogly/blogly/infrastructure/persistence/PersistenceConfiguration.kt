package com.blogly.blogly.infrastructure.persistence

import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories

@Configuration
@EnableJdbcRepositories(basePackages = ["com.blogly.blogly.domain"])
class PersistenceConfiguration
