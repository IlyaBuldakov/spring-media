package finalproject.application.dto.comment;

import finalproject.application.dto.user.UserBasicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Запрос на оставление комментария.
 */
@AllArgsConstructor
public class CommentRequestDto {

  /**
   * Возвращает @return UserBasicDto author автора коммментария.
   */
  private @Getter UserBasicDto author;

  /**
   * Возвращает @return id комментируемой задачи.
   */
  private @Getter int task;

  /**
   * Возвращает @return String message текст комментария.
   */
  private @Getter String message;


}