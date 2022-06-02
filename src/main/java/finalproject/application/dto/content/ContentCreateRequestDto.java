package finalproject.application.dto.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запрос на добавление контента.
 */
@AllArgsConstructor
public class ContentCreateRequestDto {

  /**
   * Возвращает @return byte[] file файл контента.
   */
  private @Getter byte[] file;

  /**
   * Возвращает @return task идентификатор задачи.
   */
  private @Getter int task;

}
