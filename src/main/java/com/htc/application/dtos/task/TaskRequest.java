package com.htc.application.dtos.task;

import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Запрос. Представление задачи.
 */
public class TaskRequest {
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
   * Автор.
   *
   * @see User
   *
   * @return author автор
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long authorId;

  /**
   * Исполнитель.
   *
   * @see User
   *
   * @return executor исполнитель
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long executorId;

  /**
   * Дата выполнения.
   *
   * @return dateExpired дата выполнения
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String dateExpired;

  private TaskRequest() {}
}
