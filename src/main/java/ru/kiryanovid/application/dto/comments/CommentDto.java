package ru.kiryanovid.application.dto.comments;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.application.dto.users.UserBasicDto;

/**
 * Представление сущности комментария.
 */
@AllArgsConstructor
public class CommentDto {
  /**
  * Идентификатор.
  */
  @Getter private Integer id;

  /**
   * Дата.
   */
  @Getter private LocalDateTime date;

  /**
   * Пользователь.
   */
  @Getter private UserBasicDto user;

  /**
   * Сообщение.
   */
  @Getter private String message;
}
