package com.htc.application.controllers;

import com.htc.application.dto.comment.CommentRequest;
import com.htc.application.dto.comment.CommentResponse;
import com.htc.domain.usecases.comment.CreateComment;
import com.htc.domain.usecases.comment.GetCommentsByTaskId;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с комментариями.
 */
@RestController
@RequestMapping(path = "api/comments")
@AllArgsConstructor
public class CommentController {
  private CreateComment createComment;
  private GetCommentsByTaskId getCommentsByTaskId;

  /**
   * Создаёт новый комментарий.
   */
  @PostMapping
  @Operation(summary = "Создать новый комментарий.")
  @Async
  public void create(@RequestBody CommentRequest commentRequest) {
    Controllers.handleRequest(
        createComment,
        new CreateComment.Params(
            LocalDateTime.now(),
            commentRequest.userId,
            commentRequest.taskId,
            commentRequest.commentMessage),
        null);
  }

  /**
   * Получает комментарии по идентификатору задачи.
   *
   * @param taskId Идентификатор задачи.
   * @return Комментарии по идентификатору задачи.
   */
  @GetMapping(path = "/{taskId}")
  @Operation(summary = "Получить список комментариев по идентификатору задачи.")
  @Async
  public CompletableFuture<Iterable<CommentResponse>> getAll(@PathVariable int taskId) {
    return Controllers.handleRequest(
        getCommentsByTaskId,
        new GetCommentsByTaskId.Params(taskId, "taskId"),
        comments -> comments.stream()
            .map(CommentResponse::new)
            .collect(Collectors.toList()));
  }
}
