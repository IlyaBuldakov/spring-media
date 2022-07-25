package ru.kiryanovid.application.dto.errors;


import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class NotFoundDto extends FailureException {
  /**
  * Создаёт экземпляр класса {@link FailureException}.
  *
  * @param status  Код статуса.
  * @param message Сообщение ошибки.
  */
  public NotFoundDto(HttpStatus status, String message) {
    super(status, message);
  }
}
