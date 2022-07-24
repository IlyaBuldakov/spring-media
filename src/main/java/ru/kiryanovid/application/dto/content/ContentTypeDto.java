package ru.kiryanovid.application.dto.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.task.Task;

/**
 * Представление сущности контента.
 */
@AllArgsConstructor
public class ContentTypeDto {
  /**
  * Идентификатор.
  */
  @Getter private Integer id;

  /**
  * Имя.
  */
  @Getter private String name;

  ContentTypeDto(Task task) {
    this.id = task.getContentType().getId();
    this.name = task.getContentType().getName();
  }

  public static ContentTypeDto mapToDto(Task task) {
    return new ContentTypeDto(task);
  }
}
