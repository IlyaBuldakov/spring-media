package com.htc.application.controllers;

import com.htc.application.dtos.comment.CommentRequest;
import com.htc.application.dtos.comment.CommentResponse;
import com.htc.domain.usecases.comment.AddComment;
import com.htc.domain.usecases.comment.DeleteCommentById;
import com.htc.domain.usecases.comment.GetCommentById;
import com.htc.utility.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер комментария.
 */
@RestController
@RequestMapping(path = "api/comments")
@AllArgsConstructor
public class CommentController {
  private AddComment addComment;
  private GetCommentById getCommentById;
  private DeleteCommentById deleteCommentById;

  /**
   * Добавление комментария.
   */
  @PostMapping
  @Operation(summary = "Добавить новый комментарий.")
  @Async
  public void add(@RequestBody CommentRequest commentRequest) {
    ControllerHelper.customRequest(
            addComment,
            new AddComment.Params(
                    commentRequest.getAuthorId(), "authorId",
                    commentRequest.getMessage(), "message",
                    commentRequest.getContent(), "content"
            ),
            null
    );
  }

  /**
   * Получение комментария.
   *
   * @param id идентификатор
   *
   * @return comment комментарий
   */
  @GetMapping(path = "/{id}")
  @Async
  public CompletableFuture<CommentResponse> get(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            getCommentById,
            new GetCommentById.Params(id, "id"),
            CommentResponse::new
    );
  }

  /**
   * Удаление комментария.
   *
   * @param id идентификатор
   */
  @DeleteMapping(path = "/{id}")
  @Async
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteCommentById,
            new DeleteCommentById.Params(id, "id"),
            null
    );
  }
}
