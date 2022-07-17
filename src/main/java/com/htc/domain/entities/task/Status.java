package com.htc.domain.entities.task;

import lombok.Getter;

/**
 * Статус задачи.
 */
public enum Status {
  IN_WORK(1, "В работе."),
  FEEDBACK(2, "На доработке."),
  APPROVED(3, "Утвержден.");

  /**
   * Идентификатор статуса задачи.
   *
   * @return id идентификатор статуса задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Наименование статуса задачи.
   *
   * @return name наименование статуса задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  Status(int id, String name) {
    this.id = id;
    this.name = name;
  }
}

/*
* в работе
* ожидает согласования
* выполнено
* */