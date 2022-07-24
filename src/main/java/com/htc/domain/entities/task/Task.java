package com.htc.domain.entities.task;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileName;

/**
 * Задача.
 */
public interface Task {
  /**
   * Идентификатор задачи.
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * Наименование задачи.
   *
   * @return name наименование
   */
  FileName getName();

  /**
   * {@link Type Тип} контента.
   *
   * @return type тип
   */
  Type getType();

  /**
   * Описание задачи.
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
   * {@link User Автор задачи}.
   *
   * @return author автор задачи
   */
  User getAuthor();

  /**
   * {@link User Исполнитель задачи}.
   *
   * @return executor исполнитель задачи
   */
  User getExecutor();

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  //TODO наименование DateCreated заменить на DateField
  DateCreated getDateCreated();

  /**
   * Дата выполнения.
   *
   * @return dateExpired дата выполнения
   */
  DateCreated getDateExpired();

  /**
   * {@link Content Контент}.
   *
   * @return content контент
   */
  //TODO реализовать список contents
  Content getContent();

  /**
   * {@link Comment Коммент}.
   *
   * @return comment коммент
   */
  //TODO реализовать список comments
  Comment getComment();

  /**
   * {@link Status Статус} задачи.
   *
   * @return status статус задачи
   */
  Status getStatus();
}
