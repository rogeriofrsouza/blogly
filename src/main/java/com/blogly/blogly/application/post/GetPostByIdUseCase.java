package com.blogly.blogly.application.post;

import com.blogly.blogly.application.post.dto.PostDetailsResponse;
import com.blogly.blogly.domain.post.PostId;
import com.blogly.blogly.domain.post.PostNotFoundException;
import com.blogly.blogly.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetPostByIdUseCase {

    private final PostRepository repository;

    public PostDetailsResponse execute(PostId id) {
        return repository.findById(id)
                .map(PostDetailsResponse::from)
                .orElseThrow(() -> new PostNotFoundException(id));
    }
}
