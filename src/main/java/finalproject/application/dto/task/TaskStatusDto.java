package finalproject.application.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TaskStatusDto {
  /**
   * Виды ролей пользователя.
   */
  public enum TaskStatus {
      /**
       * В работе.
       */
      INWORK,
      /**
       * Ожидает отзыва.
       */
      FEEDBACK,
      /**
       * Подтверждено.
       */
      APPROVED
  }

  /**
   * Идентификатор роли.
   *
   * @return id Идентификатор статуса
   */
  private @Getter int id;

  /**
   * Название роли.
   *
   * @return name Название статуса
   */
  private @Getter TaskStatus name;
}
