package ru.kiryanovid.domain.entity.comment;

import java.time.LocalDateTime;
import lombok.Getter;
import ru.kiryanovid.application.dto.users.UserBasicDto;

/**
 * Комментарий.
 */
public class Comment {
  /**
  * Идентификатор комментария.
  */
  @Getter private Integer id;

  /**
  * Дата создания комментария.
  */
  @Getter private LocalDateTime date;

  /**
  * Начальные данные пользователя.
  */
  @Getter private UserBasicDto user;

  /**
  * Сообщение.
  */
  @Getter private String message;
}
