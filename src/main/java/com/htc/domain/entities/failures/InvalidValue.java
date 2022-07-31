package com.htc.domain.entities.failures;

/**
 * Некорректное значение.
 */
public enum InvalidValue {

  // Сообщения общие для всех сущностей.
  /**
   * Сообщение о некорректном идентификаторе.
   */
  INVALID_ENTITY_ID("Идентификатор сущности не может быть меньше нуля.", "id"),

  // Сообщения для пользователей.
  /**
   * Сообщение о некорректном имени пользователя.
   */
  INVALID_USER_NAME("Некорректное имя пользователя.", "name"),
  /**
   * Сообщение о некорректной электронной почте пользователя.
   */
  INVALID_USER_EMAIL("Некорректная электронная почта пользователя.", "email"),
  INVALID_USER_PASSWORD("Некорректный пароль пользователя.", "password"),
  INVALID_USER_IMAGE("Некорректное изображения пользователя.", "image"),

  //Сообщения для задач.
  INVALID_TASK_NAME("Некорректное название задачи", "name"),
  INVALID_TASK_DESCRIPTION("Некорректное описание задачи", "description"),

  //Сообщения для контента.
  INVALID_FORMAT("Формат не поддерживается", null),
  INVALID_CONTENT_TYPE("Тип файла не поддерживается", null);

  InvalidValue(String message, String field) {
    this.message = message;
    this.field = field;
  }

  private final String message;

  private final String field;

  public String getMessage() {
    return message;
  }

  public String getField() {
    return field;
  }
}