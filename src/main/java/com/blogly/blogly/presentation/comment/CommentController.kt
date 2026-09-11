package com.blogly.blogly.presentation.comment

import com.blogly.blogly.application.comment.*
import com.blogly.blogly.application.comment.dto.CommentDetailsResponse
import com.blogly.blogly.application.shared.TsidCodec
import com.blogly.blogly.domain.comment.CommentId
import com.blogly.blogly.domain.post.PostId
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Tag(name = "comments")
@RestController
class CommentController(
    private val createPostCommentUseCase: CreatePostCommentUseCase,
    private val createCommentReplyUseCase: CreateCommentReplyUseCase,
    private val findAllPostCommentsUseCase: FindAllPostCommentsUseCase,
    private val updateUseCase: UpdateCommentUseCase,
    private val deleteUseCase: DeleteCommentUseCase
) {
    @PostMapping("/api/posts/{postId}/comments")
    fun createPostComment(
        @PathVariable postId: String,
        @Valid @RequestBody dto: CreateCommentDto
    ): ResponseEntity<Void> {
        val id = createPostCommentUseCase.execute(
            PostId(TsidCodec.decode(postId)), dto.toRequest()
        )

        return ResponseEntity.created(location(id)).build()
    }

    @PostMapping("/api/comments/{commentId}/replies")
    fun createReply(
        @PathVariable commentId: String,
        @Valid @RequestBody dto: CreateCommentDto
    ): ResponseEntity<Void> {
        val id = createCommentReplyUseCase.execute(
            CommentId(TsidCodec.decode(commentId)), dto.toRequest()
        )

        return ResponseEntity.created(location(id)).build()
    }

    @GetMapping("/api/posts/{postId}/comments")
    fun findAllPostComments(@PathVariable postId: String): List<CommentDetailsResponse> =
        findAllPostCommentsUseCase.execute(PostId(TsidCodec.decode(postId)))

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

    private fun location(id: CommentId) =
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/comments/{id}")
            .buildAndExpand(TsidCodec.encode(id.value))
            .toUri()
}
