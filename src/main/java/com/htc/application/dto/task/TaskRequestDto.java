package com.htc.application.dto.task;

import com.htc.application.dto.content.ContentTypeDto;
import com.htc.application.dto.file.FileDto;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.tasks.Task;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * Представление запроса задачи.
 */
public class TaskRequestDto {
  /**
   * Заголовок задачи.
   *
   * @return заголовок задачи.
   */
  private final @Getter String name;
  /**
   * Требуемный тип контента.
   *
   * @return Тип контента.
   */
  private final @Getter ContentTypeDto contentType;
  /**
   * Описание задачи.
   *
   * @return Описание задачи.
   */
  private final @Getter String description;
  /**
   * Приложеные файлы.
   *
   * @return Файлы задачи.
   */
  private final @Getter Collection<FileDto> files;
  /**
   * Автор задачи.
   *
   * @return Позьзователя - автора задачи.
   */
  private final @Getter UserResponse author;
  /**
   * Исполнитель задачи.
   *
   * @return Позьзователя - исполнителя задачи.
   */
  private final @Getter UserResponse executor;
  /**
   * Срок выполнения задачи.
   *
   * @return Срок выполнения задачи.
   */
  private final @Getter LocalDateTime dateExpired;

  /**
   * Создаёт экземпляр класса {@link TaskRequestDto}.
   *
   * @param task Сущность пользователя.
   */
  public TaskRequestDto(Task task) {
    this.name = task.getName().getValue();
    this.contentType = new ContentTypeDto(task.getContentType());
    this.description = task.getDescription().getValue();
    this.files = task.getFiles().stream()
            .map(FileDto::new)
            .collect(Collectors.toList());
    this.author = new UserResponse(task.getAuthor());
    this.executor = new UserResponse(task.getExecutor());
    this.dateExpired = task.getDateExpired();
  }
}