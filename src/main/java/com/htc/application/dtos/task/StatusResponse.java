package com.htc.application.dtos.task;

import com.htc.domain.entities.task.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности статуса.
 */
@EqualsAndHashCode
public class StatusResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Название.
   *
   * @return name название
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Создание основного представления статуса.
   *
   * @param status сущность статуса, подробнее  {@link Status}
   */
  public StatusResponse(Status status) {
    this.id = status.getId();
    this.name = status.getName();
  }
}
