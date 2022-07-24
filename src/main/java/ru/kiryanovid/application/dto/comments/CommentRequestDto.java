package ru.kiryanovid.application.dto.comments;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление сущности комментария (запрос).
 */
@AllArgsConstructor
public class CommentRequestDto {
  /**
   * Идентификатор автора.
   */
  @Getter private Integer user;

  /**
   * Идентификатор задачи.
   */
  @Getter private Integer task;

  /**
   * Сообщение.
   */
  @Getter private String message;

}
