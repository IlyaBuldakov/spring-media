package com.htc.application.services.impl;

import com.htc.application.services.CommentsService;
import com.htc.application.services.ExceptionDtoResolver;
import com.htc.domain.usecases.comments.CreateComment;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса комментариев.
 */
@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {

  CreateComment createComment;

  @Override
  public CompletableFuture<Void> createComment(String taskId, int authorId, String message) {
    return createComment.execute(taskId, authorId, message)
            .thenApply(either -> {
              if (either.isLeft()) {
                throw ExceptionDtoResolver.resolve(either.getLeft());
              }
              return null;
            });
  }
}
