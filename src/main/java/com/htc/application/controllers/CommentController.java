package com.htc.application.controllers;

import com.htc.application.dtos.comment.CommentRequest;
import com.htc.application.dtos.comment.CommentResponse;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.usecases.comment.AddComment;
import com.htc.domain.usecases.comment.DeleteCommentById;
import com.htc.domain.usecases.comment.GetCommentById;
import com.htc.utility.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@AllArgsConstructor
@RestController
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Комментарии"))
@RequestMapping(path = "api/comments")
public class CommentController {
  private AddComment addComment;
  private GetCommentById getCommentById;
  private DeleteCommentById deleteCommentById;

  /**
   * Добавление комментария.
   */
  @Operation(summary = "Добавить новый комментарий.")
  @PostMapping
  public void add(@RequestBody CommentRequest commentRequest) {
    ControllerHelper.customRequest(
            addComment,
            new AddComment.Params(
                    Id.create(commentRequest.getAuthorId()).get(),
                    commentRequest.getMessage(),
                    Id.create(commentRequest.getTaskId()).get()
            ),
            null
    );
  }

  /**
   * Получение комментария.
   *
   * @param id идентификатор
   * @return comment комментарий
   */
  @Operation(summary = "Получить комментарий по идентификатору.")
  @GetMapping(path = "/{id}")
  public CompletableFuture<CommentResponse> get(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            getCommentById,
            new GetCommentById.Params(Id.create(id).get()),
            CommentResponse::new
    );
  }

  /**
   * Удаление комментария.
   *
   * @param id идентификатор
   */
  @PreAuthorize("hasAnyAuthority('ADMIN', 'CONTENT_MAKER')")
  @Operation(summary = "Удалить комментарий по идентификатору.")
  @DeleteMapping(path = "/{id}")
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteCommentById,
            new DeleteCommentById.Params(Id.create(id).get()),
            null
    );
  }
}
