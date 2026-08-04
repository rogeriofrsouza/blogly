package com.blogly.blogly.presentation.comment

import com.blogly.blogly.application.comment.CreateCommentUseCase
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
        @PathVariable postId: String,
        @Valid @RequestBody dto: CreateCommentDto
    ): ResponseEntity<Void> {
        val id = createUseCase.execute(postId, dto.toRequest())

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri()

        return ResponseEntity.created(location).build()
    }
}
