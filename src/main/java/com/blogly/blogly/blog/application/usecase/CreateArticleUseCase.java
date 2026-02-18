package com.blogly.blogly.blog.application.usecase;

import com.blogly.blogly.blog.application.dto.CreateArticleRequest;
import com.blogly.blogly.blog.domain.Article;
import com.blogly.blogly.blog.domain.ArticleId;
import com.blogly.blogly.blog.domain.ArticleRepository;
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
