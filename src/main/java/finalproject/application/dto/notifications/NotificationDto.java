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


  private @Getter int id;

  private @Getter Type type;

  private @Getter String date;

  private @Getter String message;

  private @Getter UserBasicDto user;

  private @Getter TaskBasicDto task;


}
