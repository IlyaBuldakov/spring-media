package ru.kiryanovid.application.dto.errors;

import java.util.ArrayList;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class BadRequestDto extends FailureException {
  private final @Getter Iterable<FieldMessageDto> problems;

  /**
   * Создаёт экземпляр класса {@link FailureException}.
   *
   * @param status  Код статуса.
   * @param message Сообщение ошибки.
   */
  public BadRequestDto(HttpStatus status, String message) {
    super(status, message);
    this.problems = new ArrayList<FieldMessageDto>() {
    };
  }

  private class FieldMessageDto {
    private @Getter String field;
    private @Getter String message;

    public FieldMessageDto(String field, String message) {
      this.field = field;
      this.message = message;
    }
  }
}
