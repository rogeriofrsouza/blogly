package com.blogly.blogly.presentation.advice;

import com.blogly.blogly.application.shared.ApplicationException;
import com.blogly.blogly.application.user.UserAlreadyAdminException;
import com.blogly.blogly.domain.post.PostNotFoundException;
import com.blogly.blogly.domain.post.TitleAlreadyExistsException;
import com.blogly.blogly.domain.shared.DomainException;
import com.blogly.blogly.domain.user.EmailAlreadyExistsException;
import com.blogly.blogly.domain.user.InvalidEmailException;
import com.blogly.blogly.domain.user.InvalidNameException;
import com.blogly.blogly.domain.user.InvalidPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<ApiError> handleBadRequest(RuntimeException ex) {
        log.warn("Client error: {}", ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", ex.getMessage());
    }

    @ExceptionHandler({InvalidEmailException.class, InvalidNameException.class, InvalidPasswordException.class})
    public ResponseEntity<ApiError> handleValidation(RuntimeException ex) {
        log.info("Validation error: {}", ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", ex.getMessage());
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(PostNotFoundException ex) {
        log.info("Not found: {}", ex.getMessage());
        return build(HttpStatus.NOT_FOUND, "NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler({EmailAlreadyExistsException.class, TitleAlreadyExistsException.class, UserAlreadyAdminException.class})
    public ResponseEntity<ApiError> handleConflict(RuntimeException ex) {
        log.info("Conflict: {}", ex.getMessage());
        return build(HttpStatus.CONFLICT, "CONFLICT", ex.getMessage());
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiError> handleDomain(DomainException ex) {
        log.info("Domain error: {}", ex.getMessage());
        return build(HttpStatus.UNPROCESSABLE_CONTENT, "DOMAIN_ERROR", ex.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiError> handleApplication(ApplicationException ex) {
        log.info("Application error: {}", ex.getMessage());
        return build(HttpStatus.UNPROCESSABLE_CONTENT, "APPLICATION_ERROR", ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleGeneric(RuntimeException ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "An unexpected error occurred");
    }

    private ResponseEntity<ApiError> build(HttpStatus status, String errorKey, String message) {
        return ResponseEntity.status(status).body(new ApiError(errorKey, message));
    }
}
