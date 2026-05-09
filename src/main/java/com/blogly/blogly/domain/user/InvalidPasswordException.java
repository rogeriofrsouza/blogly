package com.blogly.blogly.domain.user;

import com.blogly.blogly.domain.shared.DomainException;

public class InvalidPasswordException extends DomainException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
