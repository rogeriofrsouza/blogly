package com.blogly.blogly.domain.user;

import com.blogly.blogly.domain.shared.DomainException;

public class InvalidEmailException extends DomainException {

    public InvalidEmailException(String email) {
        super("Invalid email format: " + email);
    }
}
