package com.blogly.blogly.infrastructure.persistence;

import com.blogly.blogly.domain.Post;
import com.blogly.blogly.domain.PostId;
import com.blogly.blogly.domain.PostRepository;
import com.blogly.blogly.domain.Title;
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
