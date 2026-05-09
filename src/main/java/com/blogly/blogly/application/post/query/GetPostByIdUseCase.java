package com.blogly.blogly.application.post.query;

import com.blogly.blogly.application.post.PostDto;
import com.blogly.blogly.domain.post.PostId;
import com.blogly.blogly.domain.post.PostNotFoundException;
import com.blogly.blogly.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetPostByIdUseCase {

    private final PostRepository repository;

    public PostDto execute(PostId id) {
        return repository.findById(id)
                .map(PostDto::from)
                .orElseThrow(() -> new PostNotFoundException(id));
    }
}
