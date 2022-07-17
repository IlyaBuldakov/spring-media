package com.htc.domain.entities.task;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileName;
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
  private @Getter Id id;

  /**
   * Наименование задачи.
   *
   * @return name наименование
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter FileName name;

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
   * {@link File Файл}.
   *
   * @return file файл
   */
  //TODO files[]
  @SuppressWarnings("JavadocDeclaration")
  private @Getter File file;

  /**
   * {@link User Автор задачи}.
   *
   * @return author автор задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter User author;

  /**
   * {@link User Исполнитель задачи}.
   *
   * @return executor исполнитель задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter User executor;

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter DateCreated dateCreated;

  /**
   * Дата выполнения.
   *
   * @return dateExpired дата выполнения
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter DateCreated dateExpired;

  /**
   * {@link Content Контент}.
   *
   * @return content контент
   */
  //TODO contents[]
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Content content;

  /**
   * {@link Comment Коммент}.
   *
   * @return comment коммент
   */
  //TODO comments[]
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Comment comment;

  /**
   * {@link Status Статус} задачи.
   *
   * @return status статус задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Status status;
}
