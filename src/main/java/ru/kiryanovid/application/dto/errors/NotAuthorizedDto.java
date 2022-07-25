package ru.kiryanovid.application.dto.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.kiryanovid.domain.entity.errors.Failure;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class NotAuthorizedDto extends FailureException {
  /**
  * Создаёт экземпляр класса {@link FailureException}.
  *
  * @param status  Код статуса.
  * @param message Сообщение ошибки.
 */
  public NotAuthorizedDto(HttpStatus status, String message) {
    super(status, message);
  }
}
