package finalproject.application.dto.comment;

import finalproject.application.dto.user.UserBasicDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Комментарий.
 */
@AllArgsConstructor
public class CommentDto {

  /**
   * Возвращает @return id идентификатор коммментария.
   */
  private @Getter int id;

  /**
   * Возвращает @return LocalDateTime date дату и время создания комментария.
   */
  private @Getter LocalDateTime date;

  /**
   * Возвращает @return UserBasicDto user автора комментария.
   */
  private @Getter UserBasicDto user;

  /**
   * Возвращает @return текст комментария.
   */
  private @Getter String message;


}
