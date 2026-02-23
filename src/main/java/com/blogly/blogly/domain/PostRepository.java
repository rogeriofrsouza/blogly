package com.blogly.blogly.domain;

import java.util.Optional;

public interface PostRepository {

    Optional<Post> findById(PostId id);

    PostId save(Post post);
}
