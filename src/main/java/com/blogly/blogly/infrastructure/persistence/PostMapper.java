package com.blogly.blogly.infrastructure.persistence;

import com.blogly.blogly.domain.post.Content;
import com.blogly.blogly.domain.post.Post;
import com.blogly.blogly.domain.post.PostId;
import com.blogly.blogly.domain.post.Title;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostEntity toEntity(Post post) {
        if (post == null) {
            return null;
        }

        PostEntity entity = new PostEntity();

        entity.setId(post.getId().value());
        entity.setTitle(post.getTitle().value());
        entity.setContent(post.getContent().value());
        entity.setStatus(post.getStatus());

        return entity;
    }

    public Post toDomain(PostEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Post(
                new PostId(entity.getId()),
                new Title(entity.getTitle()),
                new Content(entity.getContent()),
                entity.getStatus()
        );
    }
}
