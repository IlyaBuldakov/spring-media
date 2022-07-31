package com.htc.application.dto.comment;

import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.Comment;
import com.htc.domain.entities.Entity;
import java.time.LocalDateTime;

/**
 * Представление сущности комментария.
 *
 * @param id Идентификатор комментария.
 * @param date Дата создания комментария.
 * @param user Автор комментария.
 * @param message Текст комментария.
 */
public record CommentDto(
    int id,
    LocalDateTime date,
    UserShortResponse user,
    String message)
    implements Entity.View<CommentDto, Comment> {
  @Override
  public CommentDto fromEntity(Comment comment) {
    return new CommentDto(comment);
  }

  /**
   * Создаёт экземпляр класса {@link CommentDto}.
   *
   * @param comment Комментарий.
   */
  public CommentDto(Comment comment) {
    this(comment.id().getValue(),
        comment.dateCreated(),
        new UserShortResponse(comment.author()),
        comment.message().getValue());
  }
}
