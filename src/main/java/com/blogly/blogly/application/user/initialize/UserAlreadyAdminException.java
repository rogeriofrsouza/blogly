package com.blogly.blogly.application.user.initialize;

import com.blogly.blogly.application.shared.ApplicationException;

public class UserAlreadyAdminException extends ApplicationException {

    public UserAlreadyAdminException() {
        super("User is already an admin");
    }
}
