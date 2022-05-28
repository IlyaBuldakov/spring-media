package domain.entities.comments;

import domain.entities.user.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Комментарий.
 */
@AllArgsConstructor
public class Comment {
  /**
   * Идентификатор Комментария.
   *
   * @return  Идентификатор комментария.
   */
  private @Getter int id;

  /**
   * Дата создания комментария.
   *
   * @return  дату создания комментария.
   */
  private @Getter LocalDateTime date;

  /**
   * Автор комментария.
   *
   * @return  пользователя - автора комментария.
   */
  private @Getter User user;

  /**
   * Текст комментария.
   *
   * @return  текст комментария.
   */
  private @Getter String message;
}
