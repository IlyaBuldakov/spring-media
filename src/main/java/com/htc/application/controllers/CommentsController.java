package com.htc.application.controllers;

import com.htc.application.dto.comment.CommentRequest;
import com.htc.application.services.CommentsService;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с комментариями.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentsController {

  CommentsService commentsService;

  /**
   * Создание комментария.
   *
   * @param commentRequest Представление комментария (запрос).
   * @return void.
   */
  @PostMapping
  @Async
  public CompletableFuture<Void> createComment(
          @RequestBody CommentRequest commentRequest) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    int authorId = (int) securityContext.getAuthentication().getDetails();
    return commentsService.createComment(
            commentRequest.getTaskId(),
            authorId,
            commentRequest.getMessage());
  }
}
