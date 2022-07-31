package com.htc.application.dtos.comment;

import com.htc.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запрос. Основное представление сущности комментария.
 */
@AllArgsConstructor
public class CommentRequest {
  /**
   * Автор.
   *
   * @see User
   *
   * @return author автор
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long authorId;

  /**
   * Текст.
   *
   * @return message текст
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String message;

  /**
   * Контент.
   *
   * @see com.htc.domain.entities.task.Task
   *
   * @return task задача
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long taskId;

  private CommentRequest() {}
}
