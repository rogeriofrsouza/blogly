package com.blogly.blogly.application.usecase;

import com.blogly.blogly.application.dto.CreateArticleRequest;
import com.blogly.blogly.domain.Article;
import com.blogly.blogly.domain.ArticleId;
import com.blogly.blogly.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateArticleUseCase {

    private final ArticleRepository repository;

    public ArticleId execute(CreateArticleRequest request) {
        var article = new Article(request.title(), request.body(), request.summary());

        return repository.save(article);
    }
}
