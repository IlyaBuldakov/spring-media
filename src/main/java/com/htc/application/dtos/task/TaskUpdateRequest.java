package com.htc.application.dtos.task;

import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.task.Status;
import lombok.Getter;

/**
 * Запрос. Представление задачи для обновления.
 */
public class TaskUpdateRequest {
  /**
   * Наименование задачи.
   *
   * @return name наименование задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String name;

  /**
   * Тип.
   *
   * @see Type
   *
   * @return type тип
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Type type;

  /**
   * Описание задачи.
   *
   * @return description описание
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String description;

  /**
   * Файл.
   *
   * @see com.htc.domain.entities.file.File
   *
   * @return file файл
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long fileId;

  /**
   * Автор.
   *
   * @see com.htc.domain.entities.user.User
   *
   * @return author автор
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long authorId;

  /**
   * Исполнитель.
   *
   * @see com.htc.domain.entities.user.User
   *
   * @return executor исполнитель
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long executorId;

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String dateCreated;

  /**
   * Дата выполнения.
   *
   * @return dateExpired дата выполнения
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String dateExpired;

  /**
   * Контент.
   *
   * @see com.htc.domain.entities.content.Content
   *
   * @return content контент
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long contentId;

  /**
   * Коммент.
   *
   * @see com.htc.domain.entities.comment.Comment
   *
   * @return comment коммент
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long commentId;

  /**
   * Статус.
   *
   * @see Status
   *
   * @return status статус
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Status status;

  private TaskUpdateRequest() {}
}
