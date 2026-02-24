package com.blogly.blogly.infrastructure.persistence;

import com.blogly.blogly.domain.Content;
import com.blogly.blogly.domain.Post;
import com.blogly.blogly.domain.PostId;
import com.blogly.blogly.domain.Title;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostMapper {

    public PostEntity toEntity(Post post) {
        if (post == null) {
            return null;
        }

        PostEntity entity = new PostEntity();

        Optional.ofNullable(post.getId())
                .map(PostId::value)
                .ifPresent(entity::setId);

        entity.setTitle(post.getTitle().value());
        entity.setBody(post.getContent().value());
        entity.setSummary(post.getSummary());

        return entity;
    }

    public Post toDomain(PostEntity entity) {
        if (entity == null) {
            return null;
        }

        PostId id = Optional.ofNullable(entity.getId())
                .map(PostId::new)
                .orElse(null);

        return new Post(id,
                new Title(entity.getTitle()),
                new Content(entity.getBody()),
                entity.getSummary());
    }
}
