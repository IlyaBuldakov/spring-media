package com.htc.application.dto.comment;

import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.comments.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление сущности комментария.
 *
 * @param id Идентификатор комментария.
 * @param date Дата создания комментария.
 * @param user Автор комментария.
 * @param message Текст комментария.
 */
public record CommentDto(int id,
                         LocalDateTime date,
                         UserShortResponse user,
                         String message) {
    /**
   * Создаёт экземпляр класса {@link CommentDto}.
   *
   * @param comment Коментарий.
   */
  public CommentDto(Comment comment) {
    this(comment.getId().getValue(),
    comment.getDate(),
    new UserShortResponse(comment.getUser()),
    comment.getMessage().getValue());
  }
}
