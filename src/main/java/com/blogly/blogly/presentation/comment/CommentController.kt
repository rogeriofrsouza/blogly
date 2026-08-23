package com.blogly.blogly.presentation.comment

import com.blogly.blogly.application.comment.CreateCommentUseCase
import com.blogly.blogly.application.comment.DeleteCommentUseCase
import com.blogly.blogly.application.comment.FindAllCommentsUseCase
import com.blogly.blogly.application.comment.UpdateCommentUseCase
import com.blogly.blogly.application.comment.dto.CommentDetailsResponse
import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.post.PostId
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class CommentController(
    private val createUseCase: CreateCommentUseCase,
    private val findAllUseCase: FindAllCommentsUseCase,
    private val updateUseCase: UpdateCommentUseCase,
    private val deleteUseCase: DeleteCommentUseCase
) {
    @PostMapping("/api/posts/{postId}/comments")
    fun create(
        @PathVariable postId: String,
        @Valid @RequestBody dto: CreateCommentDto
    ): ResponseEntity<Void> {
        val id = createUseCase.execute(PostId(TsidCodec.decode(postId)), dto.toRequest())

        val location = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/comments/{id}")
            .buildAndExpand(TsidCodec.encode(id.value))
            .toUri()

        return ResponseEntity.created(location).build()
    }

    @GetMapping("/api/posts/{postId}/comments")
    fun findAll(@PathVariable postId: String): List<CommentDetailsResponse> =
        findAllUseCase.execute(PostId(TsidCodec.decode(postId)))

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/api/comments/{commentId}")
    fun update(
        @PathVariable commentId: String,
        @Valid @RequestBody dto: UpdateCommentDto
    ) = updateUseCase.execute(CommentId(TsidCodec.decode(commentId)), dto.toRequest())

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/comments/{commentId}")
    fun delete(@PathVariable commentId: String) =
        deleteUseCase.execute(CommentId(TsidCodec.decode(commentId)))
}
