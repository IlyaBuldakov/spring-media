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

  /**
   * Получение статуса задачи по названию задачи.
   *
   * @param name название задачи
   * @return status {@link Status статус}
   */
  public static Status getFromName(String name) {
    for (Status status : Status.values()) {
      if (status.getName().equals(name))  {
        return status;
      }
    }
    return null;
  }
}

/*
* в работе
* ожидает согласования
* выполнено
* */