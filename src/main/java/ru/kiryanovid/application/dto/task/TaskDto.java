package ru.kiryanovid.application.dto.task;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.application.dto.comments.CommentDto;
import ru.kiryanovid.application.dto.content.ContentDto;
import ru.kiryanovid.application.dto.content.ContentTypeDto;
import ru.kiryanovid.application.dto.files.FileDto;
import ru.kiryanovid.application.dto.users.UserDto;
import ru.kiryanovid.domain.entity.task.Task;


/**
 * Представление задачи.
 */
@AllArgsConstructor
public class TaskDto {
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
  @Getter private ContentTypeDto contentType;

  /**
  * Описание.
  */
  @Getter private String description;

  /**
  * Файлы.
  */
  @Getter private FileDto[] files;

  /**
  * Автор.
  */
  @Getter private UserDto author;

  /**
  * Исполнитель.
  */
  @Getter private UserDto executor;

  /**
  * Дата создания.
  */
  @Getter private LocalDateTime dateCreated;

  /**
  * Дата выполнения.
  */
  @Getter private LocalDateTime dateExpired;

  /**
  * Контент.
  */
  @Getter private ContentDto[] contents;

  /**
  * Комментарии.
  */
  @Getter private CommentDto[] comments;

  /**
  * Статус.
  */
  @Getter private TaskStatusDto status;

  /**
  * Получение нового представления задачи.
  *
  * @param task задача
  */
  public TaskDto(Task task) {
    this.id = task.getId();
    this.name = task.getName();
    this.contentType = ContentTypeDto.mapToDto(task);
    this.description = task.getDescription();
    this.files = null;
    this.author = new UserDto(task.getAuthor());
    this.executor = new UserDto(task.getExecutor());
    this.dateCreated = task.getDateCreate();
    this.dateExpired = task.getDateExpired();
    this.contents = null;
    this.comments = null;
    this.status = null;
  }
}
