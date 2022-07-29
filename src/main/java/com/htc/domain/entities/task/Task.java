package com.htc.domain.entities.task;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.Id;

/**
 * Задача.
 */
public interface Task {
  /**
   * Получить идентификатор задачи.
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * Получить наименование задачи.
   *
   * @return name наименование
   */
  EntityName getName();

  /**
   * Получить {@link Type тип} контента.
   *
   * @return type тип
   */
  Type getType();

  /**
   * Получить описание задачи.
   *
   * @return description описание
   */
  String getDescription();

  /**
   * {@link File Файл}.
   *
   * @return file файл
   */
  //TODO реализовать список files
  File getFile();

  /**
   * Получить {@link User автора задачи}.
   *
   * @return author автор задачи
   */
  User getAuthor();

  /**
   * Получить {@link User исполнителя задачи}.
   *
   * @return executor исполнитель задачи
   */
  User getExecutor();

  /**
   * Получить дату создания.
   *
   * @return dateCreated дата создания
   */
  //TODO наименование DateCreated заменить на DateField
  DateCreated getDateCreated();

  /**
   * Получить дату выполнения.
   *
   * @return dateExpired дата выполнения
   */
  DateCreated getDateExpired();

  /**
   * Получить {@link Content контент}.
   *
   * @return content контент
   */
  //TODO реализовать список contents
  Content getContent();

  /**
   * Получить {@link Comment коммент}.
   *
   * @return comment коммент
   */
  //TODO реализовать список comments
  Comment getComment();

  /**
   * Получить {@link Status статус} задачи.
   *
   * @return status статус задачи
   */
  Status getStatus();
}
