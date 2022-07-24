package com.htc.application.dto.comment;

import com.htc.domain.entities.comments.Comment;
import lombok.Getter;

/**
 * Представление запрса комментария.
 */
public class CommentRequestDto {
  /**
   * Идентификатор пользователя.
   *
   * @return Идентификатор пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int user;

  /**
   * Идентификатор задачи.
   *
   * @return Идентификатор задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int task;

  /**
   * Текст комментария.
   *
   * @return текст комментария.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String message;

  /**
   * Создаёт экземпляр класса {@link CommentRequestDto}.
   *
   * @param comment Коментарий.
   */
  public CommentRequestDto(Comment comment) {
    this.user = comment.getUser().getId().getValue();
    this.task = comment.getTask().getId().getValue();
    this.message = comment.getMessage().getValue();
  }
}