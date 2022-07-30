package com.htc.application.services.impl;

import com.htc.application.services.CommentsService;
import com.htc.application.services.ExceptionDtoResolver;
import com.htc.application.services.ServiceHelper;
import com.htc.domain.usecases.comments.CreateComment;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса комментариев.
 */
@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {

  CreateComment createComment;

  @Override
  public CompletableFuture<Void> createComment(String taskId,
                                               int authorId,
                                               Collection<? extends GrantedAuthority> authorities,
                                               String message) {
    var permissions = ServiceHelper.getPermissions(authorities);
    return createComment.execute(taskId, authorId, permissions, message)
            .thenApply(either -> {
              if (either.isLeft()) {
                throw ExceptionDtoResolver.resolve(either.getLeft());
              }
              return null;
            });
  }
}
