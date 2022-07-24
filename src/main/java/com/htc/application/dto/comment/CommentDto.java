package com.htc.application.dto.comment;

import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.comments.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление сущности комментария.
 */
public class CommentDto {
  /**
   * Идентификатор комментария.
   *
   * @return  Идентификатор комментария.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Дата создания комментария.
   *
   * @return  дату создания комментария.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime date;

  /**
   * Автор комментария.
   *
   * @return пользователя - автора комментария.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserShortResponse user;

  /**
   * Текст комментария.
   *
   * @return текст комментария.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String message;

  /**
   * Создаёт экземпляр класса {@link CommentDto}.
   *
   * @param comment Коментарий.
   */
  public CommentDto(Comment comment) {
    this.id = comment.getId().getValue();
    this.date = comment.getDate();
    this.user = new UserShortResponse(comment.getUser());
    this.message = comment.getMessage().getValue();
  }
}