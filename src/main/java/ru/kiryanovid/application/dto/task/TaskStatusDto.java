package ru.kiryanovid.application.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.task.Task;

/**
 * Представление статуса.
 */
@AllArgsConstructor
public class TaskStatusDto {
  /**
   * Идентификатор.
   */
  @Getter private Integer id;

  /**
   * Имя.
   */
  @Getter private String name;

  public TaskStatusDto(Task task) {
    this.id = task.getStatus().getId();
    this.name = task.getStatus().getName();
  }
}
