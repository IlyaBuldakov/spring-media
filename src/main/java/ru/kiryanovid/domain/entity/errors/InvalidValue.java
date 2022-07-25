package ru.kiryanovid.domain.entity.errors;

/**
 * Не валидное значение.
 */
public enum InvalidValue implements Failure {

  /**
  * Сообщение по умолчанию.
  */
  DEFAULT_MESSAGE("Некорректное значение переданного параметра."),

  /**
  * Ошибки пользователя.
  */
  INVALID_USER_NAME("Некорректное имя пользователя."),
  /**
  * Сообщение о некорректной электронной почте пользователя.
  */
  INVALID_USER_EMAIL("Некорректная электронная почта пользователя."),
  INVALID_USER_PASSWORD("Некорректный пароль пользователя."),
  INVALID_USER_IMAGE("Некорректное изображения пользователя."),

  // Сообщения общие для всех сущностей.
  ENTITY_ID_IS_NOT_A_NUMBER("Идентификатор сущности должен быть числом."),
  INVALID_ENTITY_ID("Идентификатор сущности не может быть меньше нуля."),


  // Ошибки задачи
  /**
  * Сообщение о пустом поле в названии задачи.
  */
  INVALID_TASK_NAME("Поле не может быть пустым."),
  /**
  * Дата завершения не может быть ранее даты создания задачи.
  */
  INVALID_DATE_EXPIRED("Некорректная дата завершения."),
  /**
  * Не указан тип задачи.
  */
  INVALID_TYPE_OF_CONTENT("Не указан тип задачи"),
  /**
  * Отсутствует автор задачи.
  */
  INVALID_AUTHOR("Отсутствует автор."),
  /**
  * Отсутствует исполнитель задачи.
  */
  INVALID_EXECUTOR("Отсутствует исполнитель.");

  private final String message;

  @Override
  public String getMessage() {
    return message;
  }

  InvalidValue(String message) {
    this.message = message;
  }
}
