package finalproject.application.dto.task;


import finalproject.domain.entities.task.Task;
import lombok.Getter;


/**
 * Задача - базовый класс.
 */


public class TaskBasicDto {

  /**
   * Возвращает @return id идентификатор задачи.
   */
  private final @Getter int id;

  /**
   * Возвращает @return name название задачи.
   */
  private final @Getter String name;

  public TaskBasicDto(Task task) {
    this.id = task.getId();
    this.name = task.getName();
  }
}
