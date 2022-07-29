package com.htc.application.dtos.task;

import com.htc.application.dtos.content.TypeResponse;
import com.htc.application.dtos.user.UserResponse;
import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.task.Status;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Ответ. Представление задачи.
 */
@EqualsAndHashCode
public class TaskResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Long id;

  /**
   * Наименование задачи.
   *
   * @return name Наименование задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * {@link Type Тип} контента.
   *
   * @return type тип
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter TypeResponse type;

  /**
   * Описание задачи.
   *
   * @return description описание
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String description;

  /**
   * {@link File Файл}.
   *
   * @return file файл
   */
  //TODO реализовать список files
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter File file;

  /**
   * {@link User Автор задачи}.
   *
   * @return author автор задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserResponse author;

  /**
   * {@link User Исполнитель задачи}.
   *
   * @return executor исполнитель задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserResponse executor;

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  //TODO наименование DateCreated заменить на DateField
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String dateCreated;

  /**
   * Дата выполнения.
   *
   * @return dateExpired дата выполнения
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String dateExpired;

  /**
   * {@link Content Контент}.
   *
   * @return content контент
   */
  //TODO реализовать список contents
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Content content;

  /**
   * {@link Comment Коммент}.
   *
   * @return comment коммент
   */
  //TODO реализовать список comments
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Comment comment;

  /**
   * {@link Status Статус} задачи.
   *
   * @return status статус задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter StatusResponse status;

  /**
   * Создание краткого представления задачи.
   *
   * @param task сущность задачи, подробнее {@link Task}
   */
  public TaskResponse(Task task) {
    this.id = task.getId().getValue();
    this.name = task.getName().getValue();
    this.type = new TypeResponse(task.getType());
    this.description = task.getDescription();
    this.file = task.getFile();
    this.author = new UserResponse(task.getAuthor());
    this.executor = new UserResponse(task.getExecutor());
    this.dateCreated = task.getDateCreated().getValue();
    this.dateExpired = task.getDateExpired().getValue();
    this.content = task.getContent();
    this.comment = task.getComment();
    this.status = new StatusResponse(task.getStatus());
  }
}
