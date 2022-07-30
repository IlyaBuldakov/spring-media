package com.htc.application.services;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.security.core.GrantedAuthority;

/**
 * Сервис комментариев.
 */
public interface CommentsService {

  /**
   * Создание комментария.
   *
   * @param taskId   Идентификатор задачи.
   * @param authorId Идентификатор автора.
   * @param message  Сообщение комментария.
   * @return void.
   */
  CompletableFuture<Void> createComment(String taskId,
                                        int authorId,
                                        Collection<? extends GrantedAuthority> authorities,
                                        String message);
}
