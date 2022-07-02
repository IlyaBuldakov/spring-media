package com.htc.application.dto.comment;

import com.htc.domain.entities.comments.Comment;
import lombok.Getter;

/**
 * Предсавление запрса комментария.
 */
public class CommentRequestDto {
  /**
   * Идентификатор пользователя.
   *
   * @return Идентификатор пользователя.
   */
  private final @Getter int user;

  /**
   * Идентификатор задачи.
   *
   * @return Идентификатор задачи.
   */
  private final @Getter int task;

  /**
   * Текст комментария.
   *
   * @return текст комментария.
   */
  private final @Getter String message;

  /**
   * Создаёт экземпляр класса {@link CommentRequestDto}.
   *
   * @param comment Коментарий.
   */
  public CommentRequestDto(Comment comment) {
    this.user = comment.getUser().getId();
    this.task = comment.getTask().getId();
    this.message = comment.getMessage();
  }
}