package com.htc.application.controllers;

import com.htc.application.dto.comment.CommentRequest;
import com.htc.domain.usecases.comment.CreateComment;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
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
@Tags(@Tag(name = "Комментарии"))
public class CommentController {
  private CreateComment createComment;

  /**
   * Создаёт новый комментарий.
   */
  @PostMapping
  @Operation(summary = "Добавить новый комментарий в задачу")
  @Async
  public void create(@RequestBody CommentRequest commentRequest) throws IOException {
    Controllers.handleRequest(
        createComment,
        new CreateComment.Params(
            LocalDateTime.now(),
            commentRequest.userId,
            commentRequest.taskId,
            commentRequest.commentMessage),
        null);
  }
}
