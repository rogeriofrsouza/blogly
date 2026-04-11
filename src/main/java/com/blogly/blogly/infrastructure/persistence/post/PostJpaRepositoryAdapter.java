package com.blogly.blogly.infrastructure.persistence.post;

import com.blogly.blogly.domain.post.Post;
import com.blogly.blogly.domain.post.PostId;
import com.blogly.blogly.domain.post.PostRepository;
import com.blogly.blogly.domain.post.Title;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostJpaRepositoryAdapter implements PostRepository {

    private final PostJpaRepository repository;
    private final PostMapper mapper;

    @Override
    public Optional<Post> findById(PostId id) {
        return repository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public PostId save(Post post) {
        PostEntity entity = mapper.toEntity(post);
        repository.save(entity);

        return new PostId(entity.getId());
    }

    @Override
    public boolean existsByTitle(Title title) {
        return repository.existsByTitleIgnoreCase(title.value());
    }
}
