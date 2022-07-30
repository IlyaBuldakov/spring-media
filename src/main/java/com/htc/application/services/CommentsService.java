package com.htc.application.services;

import java.util.concurrent.CompletableFuture;

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
  CompletableFuture<Void> createComment(String taskId, int authorId, String message);
}
