package finalproject.application.dto.notifications;

import finalproject.application.dto.task.TaskBasicDto;
import finalproject.application.dto.user.UserBasicDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Уведомления.
 */
@AllArgsConstructor
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
   * Возращает @return id идентификатор уведомления.
   */
  private @Getter int id;

  /**
   * Возращает @return {@link Type} type тип уведомления.
   */
  private @Getter Type type;

  /**
   * Возращает @return LocalDateTime дату уведомления.
   */
  private @Getter LocalDateTime date;

  /**
   * Возращает @return String message текст уведомления.
   */
  private @Getter String message;

  /**
   * Возращает @return UserBasicDto user базового пользователя.
   */
  private @Getter UserBasicDto user;

  /**
   * Возращает @return TaskBasicDto task базовую задачу.
   */
  private @Getter TaskBasicDto task;


}
