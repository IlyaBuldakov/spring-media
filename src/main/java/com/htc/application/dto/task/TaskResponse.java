package com.htc.application.dto.task;

import com.htc.application.dto.comment.CommentDto;
import com.htc.application.dto.content.ContentDto;
import com.htc.application.dto.content.ContentTypeDto;
import com.htc.application.dto.file.FileDto;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.tasks.Task;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * Представление сущности задачи.
 */
public class TaskResponse {
  /**
   * Индентификатор задачи.
   *
   * @return Индентификатор задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;
  /**
   * Заголовок задачи.
   *
   * @return заголовок задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;
  /**
   * Требуемный тип контента.
   *
   * @return Тип контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter ContentTypeDto contentType;
  /**
   * Описание задачи.
   *
   * @return Описание задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String description;
  /**
   * Приложеные файлы..
   *
   * @return Файлы задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Collection<FileDto> files;
  /**
   * Автор задачи.
   *
   * @return Позьзователя - автора задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserResponse author;
  /**
   * Исполнитель задачи.
   *
   * @return Позьзователя - исполнителя задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserResponse executor;
  /**
   * Дата создания задачи.
   *
   * @return Дату создания задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime dateCreated;
  /**
   * Срок выполнения задачи.
   *
   * @return Срок выполнения задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime dateExpired;
  /**
   * Контент - результат выполнения задачи.
   *
   * @return Контент.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Collection<ContentDto> contents;
  /**
   * Коментарии задачи.
   *
   * @return Коментарии задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Collection<CommentDto> comments;
  /**
   * Статус задачи.
   *
   * @return Статус задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter TaskStatusDto status;


  /**
   * Создаёт экземпляр класса {@link TaskResponse}.
   *
   * @param task Сущность пользователя.
   */
  public TaskResponse(Task task) {
    this.id = task.getId().getValue();
    this.name = task.getName().getValue();
    this.contentType = new ContentTypeDto(task.getContentType());
    this.description = task.getDescription().getValue();
    this.files = task.getFiles().stream()
            .map(FileDto::new)
            .collect(Collectors.toList());
    this.author = new UserResponse(task.getAuthor());
    this.executor = new UserResponse(task.getExecutor());
    this.dateCreated = task.getDateCreated();
    this.dateExpired = task.getDateExpired();
    this.contents = task.getContents().stream()
            .map(ContentDto::new)
            .collect(Collectors.toList());
    this.comments = task.getComments().stream()
            .map(CommentDto::new)
            .collect(Collectors.toList());
    this.status = new TaskStatusDto(task.getStatus());
  }
}
