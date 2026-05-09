package com.blogly.blogly.domain.user;

import com.blogly.blogly.domain.shared.DomainException;

public class InvalidNameException extends DomainException {

    public InvalidNameException(String message) {
        super(message);
    }
}
