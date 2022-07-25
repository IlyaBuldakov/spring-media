package ru.kiryanovid.application.dto.task;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.application.dto.content.ContentTypeDto;
import ru.kiryanovid.application.dto.users.UserBasicDto;
import ru.kiryanovid.domain.entity.task.Task;


/**
* Представление задачи для списка.
*/
@AllArgsConstructor
public class TaskListDto {
  /**
  * Идентификатор.
  */
  @Getter private Integer id;

  /**
  * Имя.
  */
  @Getter private String name;

  /**
  * Тип контента.
  */
  @Getter private ContentTypeDto type;

  /**
  * Исполнитель.
  */
  @Getter private UserBasicDto executor;

  /**
  * Дата исполнения.
  */
  @Getter private LocalDateTime dateExpired;

  /**
  * Статус.
  */
  @Getter private TaskStatusDto status;

  /**
  * Получение представления.
  *
  * @param task задача
  */
  public TaskListDto(Task task) {
    this.id = task.getId();
    this.name = task.getName();
    this.type = ContentTypeDto.mapToDto(task);
    this.executor = new UserBasicDto(task.getExecutor());
    this.dateExpired = task.getDateExpired();
    this.status = null;
  }
}

