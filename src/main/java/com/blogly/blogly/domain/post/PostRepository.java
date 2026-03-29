package com.blogly.blogly.domain.post;

import java.util.Optional;

public interface PostRepository {

    Optional<Post> findById(PostId id);

    PostId save(Post post);

    boolean existsByTitle(Title title);
}
