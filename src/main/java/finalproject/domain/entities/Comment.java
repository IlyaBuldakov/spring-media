package finalproject.domain.entities;

import finalproject.domain.entities.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

public class Comment {

  /**
   * Возвращает @return id идентификатор коммментария.
   */
  private @Getter int id;

  /**
   * Возвращает @return id комментируемой задачи.
   */
  private @Getter int task;

  /**
   * Возвращает @return LocalDateTime date дату и время создания комментария.
   */
  private @Getter LocalDateTime date;

  /**
   * Возвращает @return автора комментария.
   */
  private @Getter User user;

  /**
   * Возвращает @return текст комментария.
   */
  private @Getter String message;
}
