package com.htc.application.dtos.task;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.task.Status;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Ответ. Представление задачи.
 */
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
  private final @Getter Type type;

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
  private final @Getter User author;

  /**
   * {@link User Исполнитель задачи}.
   *
   * @return executor исполнитель задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter User executor;

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
  private final @Getter Status status;

  /**
   * Создание краткого представления задачи.
   *
   * @param task сущность задачи, подробнее {@link Task}
   */
  public TaskResponse(Task task) {
    this.id = task.getId().getValue();
    this.name = task.getName().getValue();
    this.type = task.getType();
    this.description = task.getDescription();
    this.file = task.getFile();
    this.author = task.getAuthor();
    this.executor = task.getExecutor();
    this.dateCreated = task.getDateCreated().getValue();
    this.dateExpired = task.getDateExpired().getValue();
    this.content = task.getContent();
    this.comment = task.getComment();
    this.status = task.getStatus();
  }
}
