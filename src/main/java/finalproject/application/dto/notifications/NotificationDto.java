package finalproject.application.dto.notifications;

import finalproject.application.dto.task.TaskBasicDto;
import finalproject.application.dto.user.UserBasicDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * Уведомления.
 */
@AllArgsConstructor
@Getter
@Setter
public class NotificationDto {

  /**
   * Тип.
   */
  public enum Type {
    /**
     * Видео.
     */
    VIDEO,
    /**
     * Аудио.
     */
    AUDIO,
    /**
     * Фото.
     */
    PHOTO,
    /**
     * Комментарий.
     */
    COMMENT,
    /**
     * Контент.
     */
    CONTENT
  }

  /**
   * @return id идентификатор уведомления.
   */
  private @Getter int id;

  /**
   * @return {@link Type} type тип уведомления.
   */
  private @Getter Type type;

  /**
   * @return LocalDateTime дату уведомления.
   */
  private @Getter String date;

  /**
   * @return String message текст уведомления.
   */
  private @Getter String message;

  /**
   * @return UserBasicDto user базового пользователя.
   */
  private @Getter UserBasicDto user;

  /**
   * @return TaskBasicDto task базовую задачу.
   */
  private @Getter TaskBasicDto task;


}
