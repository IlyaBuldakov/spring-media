package com.htc.domain.entities.task;

import com.htc.domain.entities.content.Type;
import lombok.Getter;

/**
 * Задача.
 */
public class Task {
  /**
   * Идентификатор задачи.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int id;

  /**
   * Наименование задачи.
   *
   * @return name наименование
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String name;

  /**
   * {@link Type Тип} контента.
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
   * Идентификаторы {@link com.htc.domain.entities.file.File файлов}.
   *
   * @return array идентификаторы файлов
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int[] files;

  /**
   * Идентификатор {@link com.htc.domain.entities.user.User автора задачи}.
   *
   * @return id идентификатор автора
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int authorId;

  /**
   * Идентификатор {@link com.htc.domain.entities.user.User исполнителя задачи}.
   *
   * @return id идентификатор исполнителя
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int executorId;

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
   * Идентификаторы {@link com.htc.domain.entities.content.Content контента}.
   *
   * @return array идентификаторы контента
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int[] contents;

  /**
   * Идентификаторы {@link com.htc.domain.entities.comment.Comment комментов}.
   *
   * @return array идентификаторы комментов
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int[] comments;

  /**
   * {@link Status Статус} задачи.
   *
   * @return status статус задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Status status;
}
