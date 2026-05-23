package com.blogly.blogly.application.auth;

import com.blogly.blogly.application.exception.ApplicationException;

public class InvalidCredentialsException extends ApplicationException {

    public InvalidCredentialsException() {
        super("Invalid email or password");
    }
}
