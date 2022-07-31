package com.htc.application.dto.comment;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.Entity;

/**
 * Представление запроса комментария.
 *
 * @param user Идентификатор пользователя.
 * @param task Идентификатор задачи.
 * @param message Текст комментария.
 */
public record CommentRequestDto(
    int user,
    int task,
    String message)
    implements Entity.View<CommentRequestDto, Comment> {
  @Override
  public CommentRequestDto fromEntity(Comment comment) {
    return new CommentRequestDto(comment);
  }

  /**
   * Создаёт экземпляр класса {@link CommentRequestDto}.
   *
   * @param comment Комментарий.
   */
  public CommentRequestDto(Comment comment) {
    this(comment.author().id().getValue(),
        comment.task().id().getValue(),
        comment.message().getValue());
  }
}