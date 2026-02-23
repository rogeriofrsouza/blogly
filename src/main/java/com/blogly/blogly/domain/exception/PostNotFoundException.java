package com.blogly.blogly.domain.exception;

import com.blogly.blogly.domain.PostId;

public class PostNotFoundException extends DomainException {

    public PostNotFoundException(PostId id) {
        super("Post not found for id: " + id);
    }
}
