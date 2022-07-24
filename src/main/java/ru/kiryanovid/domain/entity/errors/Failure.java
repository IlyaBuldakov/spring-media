package ru.kiryanovid.domain.entity.errors;

/**
 * Ошибка.
 */
public interface Failure {
  /**
  * Получить сообщение ошибки.
  *
  * @return Сообщение ошибки.
  */
  String getMessage();
}
