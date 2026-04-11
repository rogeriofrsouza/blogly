package com.blogly.blogly.domain.exception;

public class InvalidEmailException extends DomainException {

    public InvalidEmailException(String email) {
        super("Invalid email format: " + email);
    }
}
