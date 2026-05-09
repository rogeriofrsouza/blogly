package com.blogly.blogly.application.usecase;

import com.blogly.blogly.application.exception.ApplicationException;

public class UserAlreadyAdminException extends ApplicationException {

    public UserAlreadyAdminException() {
        super("User is already an admin");
    }
}
