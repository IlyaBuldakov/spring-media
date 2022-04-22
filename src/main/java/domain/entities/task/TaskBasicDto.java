package domain.entities.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Задача - базовый класс.
 */

@NoArgsConstructor
@AllArgsConstructor
public class TaskBasicDto {

  /**
   * Возвращает @return id идентификатор задачи.
   */
  private @Getter int id;

  /**
   * Возвращает @return name название задачи.
   */
  private @Getter String name;


}
