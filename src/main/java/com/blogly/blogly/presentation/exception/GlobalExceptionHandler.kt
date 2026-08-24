package com.blogly.blogly.presentation.exception

import com.blogly.blogly.application.auth.InvalidCredentialsException
import com.blogly.blogly.application.exception.ApplicationException
import com.blogly.blogly.application.user.AdminPrivilegeRequiredException
import com.blogly.blogly.application.user.UserAlreadyAdminException
import com.blogly.blogly.domain.exception.DomainException
import com.blogly.blogly.domain.exception.NotFoundException
import com.blogly.blogly.domain.exception.NotOwnedException
import com.blogly.blogly.domain.post.TitleAlreadyExistsException
import com.blogly.blogly.domain.user.exception.EmailAlreadyExistsException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import kotlin.time.Clock
import kotlin.time.toJavaInstant

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException): ResponseEntity<ApiError> =
        build(HttpStatus.BAD_REQUEST, ex)

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException): ResponseEntity<ApiError> =
        build(HttpStatus.NOT_FOUND, ex)

    @ExceptionHandler(
        EmailAlreadyExistsException::class, TitleAlreadyExistsException::class,
        UserAlreadyAdminException::class, ApplicationException::class
    )
    fun handleConflict(ex: RuntimeException): ResponseEntity<ApiError> =
        build(HttpStatus.CONFLICT, ex)

    @ExceptionHandler(NotOwnedException::class, AdminPrivilegeRequiredException::class)
    fun handleForbidden(ex: RuntimeException): ResponseEntity<ApiError> =
        build(HttpStatus.FORBIDDEN, ex)

    @ExceptionHandler(DomainException::class)
    fun handleDomain(ex: DomainException): ResponseEntity<ApiError> =
        build(HttpStatus.UNPROCESSABLE_CONTENT, ex)

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleUnauthorized(ex: RuntimeException): ResponseEntity<ApiError> =
        build(HttpStatus.UNAUTHORIZED, ex)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<ApiError> {
        val errors = ex.bindingResult.fieldErrors.map { error ->
            "${error.field}: ${error.defaultMessage}"
        }
        val status = HttpStatus.BAD_REQUEST
        log.error("{}: {}", status, ex.message)

        return ResponseEntity
            .status(status)
            .body(
                ApiError(Clock.System.now().toJavaInstant(), status.value(), "Validation failed", errors)
            )
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleGeneric(ex: RuntimeException): ResponseEntity<ApiError> =
        build(HttpStatus.INTERNAL_SERVER_ERROR, ex)

    private fun build(status: HttpStatus, exception: RuntimeException): ResponseEntity<ApiError> {
        log.error("{}: {}", status, exception.message, exception)

        return ResponseEntity
            .status(status)
            .body(
                ApiError(Clock.System.now().toJavaInstant(), status.value(), exception.message ?: "Unknown error")
            )
    }

    companion object {
        private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }
}
