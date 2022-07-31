package finalproject.application.controllers;

import finalproject.application.dto.comment.CommentRequestDto;
import finalproject.application.dto.failures.FailureConverter;
import finalproject.application.services.AuthService;
import finalproject.application.services.CommentService;
import finalproject.domain.entities.Comment;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер комментариев.
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/comments")
public class CommentController {

  CommentService commentService;
  AuthService authService;

  /**
   * Создание комментария.
   *
   * @param commentData DTO запроса
   * @return комментарий
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PostMapping
  public CompletableFuture<Comment> postComment(@RequestBody CommentRequestDto commentData) {
    return commentService.postCommentToTask(commentData.getAuthor(),
            commentData.getTask(), commentData.getMessage())
            .thenApply(either -> either.getOrElseThrow(
              failure -> FailureConverter.convert(failure)));
  }

  /**
   * Удаление комментария.
   *
   * @param id комментария
   * @return ничего не возвращает.
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @DeleteMapping
  public CompletableFuture<Void> deleteComment(@RequestParam int id) {
    int auth = authService.getId();
    return commentService.deleteCommentById(id, auth).thenApply(either -> either.getOrElseThrow(
            failure -> FailureConverter.convert(failure)));
  }

}
