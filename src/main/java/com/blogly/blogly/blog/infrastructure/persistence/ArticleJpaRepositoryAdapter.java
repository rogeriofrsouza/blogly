package com.blogly.blogly.blog.infrastructure.persistence;

import com.blogly.blogly.blog.domain.Article;
import com.blogly.blogly.blog.domain.ArticleId;
import com.blogly.blogly.blog.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ArticleJpaRepositoryAdapter implements ArticleRepository {

    private final ArticleJpaRepository repository;
    private final ArticleMapper mapper;

    @Override
    public Optional<Article> findById(ArticleId id) {
        return repository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public ArticleId save(Article article) {
        ArticleEntity entity = mapper.toEntity(article);
        repository.save(entity);

        return new ArticleId(entity.getId());
    }
}
