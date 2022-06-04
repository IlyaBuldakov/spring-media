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
   * @return id - идентификатор
   */
  private @Getter int id;

  /**
   * Наименование задачи.
   *
   * @return name - наименование
   */
  private @Getter String name;

  /**
   * {@link Type Тип} контента.
   *
   * @return type - тип
   */
  private @Getter Type type;

  /**
   * Описание задачи.
   *
   * @return description - описание
   */
  private @Getter String description;

  /**
   * Идентификаторы {@link com.htc.domain.entities.file.File файлов}.
   *
   * @return array - идентификаторы файлов
   */
  private @Getter int[] files;

  /**
   * Идентификатор {@link com.htc.domain.entities.user.User автора задачи}.
   *
   * @return id - идентификатор автора
   */
  private @Getter int authorId;

  /**
   * Идентификатор {@link com.htc.domain.entities.user.User исполнителя задачи}.
   *
   * @return id - идентификатор исполнителя
   */
  private @Getter int executorId;

  /**
   * Дата создания.
   *
   * @return dateCreated - дата создания
   */
  private @Getter String dateCreated;

  /**
   * Дата выполнения.
   *
   * @return dateExpired - дата выполнения
   */
  private @Getter String dateExpired;

  /**
   * Идентификаторы {@link com.htc.domain.entities.content.Content контента}.
   *
   * @return array - идентификаторы контента
   */
  private @Getter int[] contents;

  /**
   * Идентификаторы {@link com.htc.domain.entities.comment.Comment комментов}.
   *
   * @return array - идентификаторы комментов
   */
  private @Getter int[] comments;

  /**
   * {@link Status Статус} задачи.
   *
   * @return status - статус задачи
   */
  private @Getter Status status;
}
