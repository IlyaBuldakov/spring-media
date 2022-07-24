package com.htc.application.dto.comment;

import com.htc.domain.entities.comments.Comment;
import lombok.Getter;

/**
 * Представление запрса комментария.
 *
 * @param user Идентификатор пользователя.
 * @param task Идентификатор задачи.
 * @param message Текст комментария.
 */
public record CommentRequestDto(int user,
                                int task,
                                String message) {
    /**
   * Создаёт экземпляр класса {@link CommentRequestDto}.
   *
   * @param comment Коментарий.
   */
  public CommentRequestDto(Comment comment) {
    this(comment.getUser().getId().getValue(),
    comment.getTask().getId().getValue(),
    comment.getMessage().getValue());
  }
}