package com.htc.application.dto.comment;

import com.htc.domain.entities.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление сущности комментария (ответ на запрос).
 */
public class CommentResponse {
  /**
   * Идентификатор комментария.
   *
   * @return id Идентификатор комментария.
   */
  private final @Getter int id;

  /**
   * Дата создания комментария.
   *
   * @return dateCreateComment Дата создания комментария.
   */
  private final @Getter LocalDateTime dateCreateComment;

  /**
   * Идентификатор пользователя создавшего комментарий.
   *
   * @return userId Идентификатор пользователя создавшего комментарий.
   */
  private final @Getter int userId;

  /**
   * Сообщение комментария.
   *
   * @return Сообщение комментария.
   */
  private final @Getter String message;

  /**
   * Создаёт экземпляр класса {@link CommentResponse}.
   *
   * @param comment Сущность комментария.
   */
  public CommentResponse(Comment comment) {
    this.id = comment.getId().getValue();
    this.dateCreateComment = comment.getDateCreateComment();
    this.userId = comment.getUserId();
    this.message = comment.getCommentMessage();
  }
}
