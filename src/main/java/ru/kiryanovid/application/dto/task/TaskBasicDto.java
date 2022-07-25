package ru.kiryanovid.application.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Простое представление задачи.
 */
@AllArgsConstructor
public class TaskBasicDto {
  /**
  * Идентификатор задания.
  */
  @Getter private Integer id;

  /**
  * Название задания.
  */
  @Getter private String name;
}
