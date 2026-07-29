package com.blogly.blogly.presentation.comment

import com.blogly.blogly.application.comment.CreateCommentUseCase
import com.blogly.blogly.domain.post.PostId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RequestMapping("/api/posts/{postId}/comments")
@RestController
class CommentController(
    private val createUseCase: CreateCommentUseCase
) {
    @PostMapping
    fun create(
        @PathVariable postId: Long,
        @Valid @RequestBody dto: CreateCommentDto
    ): ResponseEntity<Void> {
        val id = createUseCase.execute(PostId(postId), dto.toRequest())

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id.value)
            .toUri()

        return ResponseEntity.created(location).build()
    }
}
