package com.blogly.blogly.infrastructure.bootstrap

import com.blogly.blogly.application.user.InitializeAdminUseCase
import com.blogly.blogly.application.user.UserAlreadyAdminException
import com.blogly.blogly.application.user.dto.InitializeAdminRequest
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class AdminUserInitializer(
    private val useCase: InitializeAdminUseCase,
    private val adminProperties: AdminProperties
) : CommandLineRunner {

    override fun run(vararg args: String) {
        try {
            log.debug("Starting admin user initialization")

            val request = InitializeAdminRequest(
                email = adminProperties.email,
                password = adminProperties.password,
                name = adminProperties.name
            )

            useCase.execute(request)
            log.info("Admin user initialization process finished")
        } catch (_: UserAlreadyAdminException) {
            log.info("Admin user is already initialized, skipping initialization")
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(AdminUserInitializer::class.java)
    }
}
