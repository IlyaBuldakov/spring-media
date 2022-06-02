package finalproject.application.dto.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запрос файла.
 */
@AllArgsConstructor
public class FileRequestDto {

  /**
   * Возвращает файл.
   */
  private @Getter byte[] file;

  /**
   * Возвращает @return task идентификатор задачи.
   */
  private @Getter int task;




}
