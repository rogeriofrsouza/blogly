package com.blogly.blogly.domain.post;

import com.blogly.blogly.domain.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {

    public PostNotFoundException(PostId id) {
        super(Post.class, id.value());
    }
}
