package com.blogly.blogly.domain.post;

import com.blogly.blogly.domain.shared.DomainException;

public class PostNotFoundException extends DomainException {

    public PostNotFoundException(PostId id) {
        super("Post not found for id: " + id);
    }
}
