package com.htc.application.dto.comment;

import com.htc.application.dto.BaseResponse;
import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.comment.Comment;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление комментария (ответ).
 */
@AllArgsConstructor
public class CommentResponse implements BaseResponse {

  /**
   * Конструктор из соответствующей сущности.
   * Используется Reflection API извне.
   *
   * @param comment Сущность {@link Comment комментария}.
   */
  public CommentResponse(Comment comment) {
    this.id = comment.getId();
    this.dateCreated = comment.getDateCreated();
    this.author = new UserShortResponse(comment.getAuthor());
    this.message = comment.getMessage();
  }

  /**
   * Идентификатор комментария.
   */
  private @Getter int id;

  /**
   * Дата создания комментария.
   */
  private @Getter LocalDate dateCreated;

  /**
   * Автор комментария.
   */
  private @Getter UserShortResponse author;

  /**
   * Сообщение (содержимое) комментария.
   */
  private @Getter String message;
}
